package com.dawei.boot.boothelloword.fusedegradation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.log.LogBase;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.dawei.boot.boothelloword.utils.ThreadPoolUtil;

/**
 * Sentinel 实现熔断和降级的集中方式
 *
 */
public class SentinelDemo {

	//1、主流框架整合

	private Object object = new Object();

	/**
	 * 2、异常抛出的方式
	 * 2.1 无热点数据参数时的使用方法
	 */
	public void method2(String resourceName) {
		int times = 100;

		while (times-- > 0){
			synchronized (object) {
			///资源名可使用任意有业务语义的字符串，比如方法名、接口名或其它可唯一标识的字符串。
			try (Entry entry = SphU.entry(resourceName)) {
				// 被保护的业务逻辑
				System.out.println("Get resource" + times);
				System.out.println(System.currentTimeMillis());
				Thread.sleep(1000L);
			} catch (BlockException ex) {
				System.out.println("Can`t get resource" + times);
				ex.printStackTrace();
				// 资源访问阻止，被限流或被降级
				// 在此处进行相应的处理操作
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		}
	}

	/**
	 * 2.2 带热点参数的校验使用方法，
	 */
	public void method2_2() {
		while (true) {
			///资源名可使用任意有业务语义的字符串，比如方法名、接口名或其它可唯一标识的字符串。

			Entry entry = null;
			try {
				// EntryType 代表流量类型，其中系统规则只对 IN 类型的埋点生效
				// count 大多数情况都填 1，代表统计为一次调用。
				entry = SphU.entry("resourceName-method2-2", EntryType.IN, 1, "param1", "param2");

				System.out.println("Get resource : resourceName-method2-2");
			} catch (BlockException e) {
				System.out.println("Can`t get resource, e=" + e.getMessage());
				e.printStackTrace();
			} catch (Exception ex) {
				// 若需要配置降级规则，需要通过这种方式记录业务异常, 记录降级统计数据
				Tracer.traceEntry(ex, entry);
				System.out.println("Mark error");
			} finally {
				// 务必保证 exit，务必保证每个 entry 与 exit 配对, 成对出现
				if (entry != null) {
					entry.exit();
				}
			}
		}
	}

	/**
	 * 3 返回布尔值的方法去定义资源
	 */
	public void method3() {
		while (true) {
			// 资源名可使用任意有业务语义的字符串
			if (SphO.entry("resourceName-method3")) {
				// 务必保证finally会被执行
				try {
					System.out.println("Get resource : resourceName-method3");
				} finally {
					SphO.exit();
				}
			} else {
				// 资源访问阻止，被限流或被降级
				// 进行相应的处理操作
				System.out.println("Can`t get resource");
			}
		}
	}

	/**
	 * 	4 通过注解的方式定义资源
	 * 注意 blockHandler 函数会在原方法被限流/降级/系统保护的时候调用，
	 *   而 fallback 函数会针对所有类型的异常
	 *   若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑。
	 *   若未配置 blockHandler、fallback 和 defaultFallback，则被限流降级时会将 BlockException 直接抛出（若方法本身未定义 throws BlockException 则会被 JVM 包装一层 UndeclaredThrowableException）。
	 */
	@SentinelResource(value = "resourceName-method3", blockHandler = "methodBlockHandler", fallback = "methodFallback")
	public String method4(String paramA) {
		throw new RuntimeException("Run method failed. paramA=" + paramA);
	}
	//4-handler 通过注解的方式定义资源
	public String methodBlockHandler(String paramA, BlockException blockException) {
		System.out.println("Run methodBlockHandler whit paramA = " + paramA);
		blockException.printStackTrace();
		return "Run methodBlockHandler whit paramA = " + paramA;
	}
	//4-fallback 通过注解的方式定义资源
	public String methodFallback(String paramA, BlockException blockException) {
		System.out.println("methodFallback ");
		return "Run methodBlockHandler whit paramA = " + paramA;
	}

	/**
	 * 	5 通过异步调用
	 * 注意 blockHandler 函数会在原方法被限流/降级/系统保护的时候调用，
	 *   而 fallback 函数会针对所有类型的异常
	 */
	public void method5_1() {
		while (true) {
			try {
				final AsyncEntry asyncEntry = SphU.asyncEntry("resourceName-method3");
				try {
					ThreadPoolUtil.submitTask(() -> {
						System.out.println("Do something");
					}, "actionMark");
				} finally {
					//在回调之后 执行
					asyncEntry.exit();
				}
			} catch (BlockException e) {
				System.out.println("Can`t get resource : resourceName-method3");
			}
		}
	}

	/**
	 * 	5-2 通过异步调用
	 * 注意 blockHandler 函数会在原方法被限流/降级/系统保护的时候调用，
	 *   而 fallback 函数会针对所有类型的异常
	 */
	public void method5_2_1() {
		Entry entry = null;
		try {
			entry = SphU.entry("resourceName-method5_2_1");
			// Handle your result here.
		} catch (BlockException ex) {
			// Blocked for the result handler.
		} finally {
			if (entry != null) {
				entry.exit();
			}
		}
	}

	public void method5_2_2() {
		try {
			AsyncEntry entry = SphU.asyncEntry("resourceName-method5_2_2");
			// Asynchronous invocation.
			ThreadPoolUtil.submitTask(() -> {
				// 在异步回调中进行上下文变换，通过 AsyncEntry 的 getAsyncContext 方法获取异步 Context
				ContextUtil.runOnContext(entry.getAsyncContext(), () -> {
					try {
						// 此处嵌套正常的资源调用.
						method5_2_1();
					} finally {
						entry.exit();
					}
				});
			}, "method5_2_2-mark");
		} catch (BlockException ex) {
			// Request blocked.
			// Handle the exception (e.g. retry or fallback).
		}
	}





	//QPS
	public void initFlowQpsRule(String resourceName) {
		List<FlowRule> rules = new ArrayList<>();
		FlowRule rule = new FlowRule(resourceName);
		// set limit qps to 20
		rule.setCount(1);
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rule.setLimitApp("default");
		//
		rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP_RATE_LIMITER);
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}


	//熔断降级规则
	private void initDegradeRule(String resourceName) {
		List<DegradeRule> rules = new ArrayList<>();
		DegradeRule rule = new DegradeRule();
		rule.setResource(resourceName);
		// set threshold RT, 10 ms
		rule.setCount(10);
		rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
		//单位 s
		rule.setTimeWindow(5);
		rules.add(rule);
		DegradeRuleManager.loadRules(rules);
	}

	//权限
	private void initAuthorityRule() {
		List<AuthorityRule> rules = new ArrayList<>();
		AuthorityRule rule = new AuthorityRule();
		rule.setResource("resourceName");
		rule.setLimitApp("AppA,appB");
		rule.setStrategy(RuleConstant.AUTHORITY_WHITE);
		rules.add(rule);
		AuthorityRuleManager.loadRules(rules);
	}




	public static void main(String[] args) {

		String resourceName = "resourceName";


		System.setProperty(LogBase.LOG_DIR,  System.getProperty("user.home") + "/logs");
		String relativelyPath=System.getProperty("user.dir");
		SentinelDemo sentinelDemo = new SentinelDemo();

		System.out.println(SentinelDemo.class.getClassLoader().getResource("").getPath());
		//sentinelDemo.initDegradeRule(resourceName);

		sentinelDemo.initFlowQpsRule(resourceName);
		SentinelDemo sentinelDemo1 = new SentinelDemo();
		sentinelDemo1.initFlowQpsRule(resourceName);

		new Thread(() -> {sentinelDemo.method2(resourceName);}).start();
		new Thread(() -> {sentinelDemo1.method2(resourceName);}).start();


	}
}
