package com.dawei.boot.boothelloword.controller.fuse;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.reflection.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.dawei.boot.boothelloword.fusedegradation.SentinelDemo;
import com.dawei.boot.boothelloword.utils.GsonUtil;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping(value = "/api/test/fuse")
public class FuseDowngradeTestController {

	private static final Logger logger = LoggerFactory.getLogger(FuseDowngradeTestController.class);


	@Autowired
	private TestService testService;

	@GetMapping("/sentinel")
	public String testSentinel(HttpServletRequest request, Integer count) throws InterruptedException {
		logger.info("Push Send Message  count={}", count);
		String resourceName = "resourceName";

		SentinelDemo sentinelDemo = new SentinelDemo();
		//sentinelDemo.initDegradeRule(resourceName);

		sentinelDemo.initFlowQpsRule(resourceName);
		SentinelDemo sentinelDemo1 = new SentinelDemo();
		sentinelDemo1.initFlowQpsRule(resourceName);

		new Thread(() -> {sentinelDemo.method2(resourceName);}).start();
		new Thread(() -> {sentinelDemo1.method2(resourceName);}).start();
		String abc = request.getParameter("abc");
		System.out.println(abc);
		List<String> boardIdList = GsonUtil.fromJson(abc,
				new TypeToken<List<String>>() {
				}.getType());
		init(count);
		String result = "";
		Entry entry = null;
		try {
			entry =  SphU.entry("resourceName-method3");
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (BlockException e) 	{
			throw new RuntimeException("Error");
		} catch (Exception e) {
			e.printStackTrace();
			// 若需要配置降级规则，需要通过这种方式记录业务异常, 记录降级统计数据
			Tracer.traceEntry(e, entry);
		} finally {
			// 务必保证 exit，务必保证每个 entry 与 exit 配对, 成对出现
			if (entry != null) {
				entry.exit();
			}
		}
		;
		result = testService.method4("xxzsdsd");

		// 资源名可使用任意有业务语义的字符串

		if (SphO.entry("resourceName-method3")) {
			// 务必保证finally会被执行
			try {
				TimeUnit.MILLISECONDS.sleep(200);
				//logger.info("Get resource : resourceName-method3");
				result = "Get resource : resourceName-method3";
			} catch (InterruptedException e) {
				// 若需要配置降级规则，需要通过这种方式记录业务异常, 记录降级统计数据
				Tracer.traceEntry(e, entry);
			} finally {
				SphO.exit();
			}
		} else {
			// 资源访问阻止，被限流或被降级
			// 进行相应的处理操作
			logger.error("Can`t get resource");
			result = "Can`t get resource";
			throw new RuntimeException("Error");
		}



		return result;
	}


	private boolean init  = true;






	private void init(Integer count)  {
		if (init) {
			init = false;
		}

//		//流量控制 QPS
		List<FlowRule> flowRuleList = new ArrayList<>();
		FlowRule flowRule = new FlowRule("resourceName-method3");
		// set limit qps to 20
		flowRule.setCount(count);
		flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		flowRule.setLimitApp("default");
		flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP);
		flowRuleList.add(flowRule);
		FlowRuleManager.loadRules(flowRuleList);

//		//降级
//		List<DegradeRule> degradeRuleList = new ArrayList<>();
//		DegradeRule degradeRule = new DegradeRule();
//		degradeRule.setResource("resourceName-method3");
//		// set threshold RT, 10 ms
//		degradeRule.setCount(2);
//		degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
//
//		// 异常数数量 2个的时候 中断
//		degradeRule.setCount(2);
//		degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
//
//
//		degradeRule.setTimeWindow(3);
//		degradeRuleList.add(degradeRule);
//		DegradeRuleManager.loadRules(degradeRuleList);
	}




}
