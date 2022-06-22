package com.dawei.boot.boothelloword.controller.fuse;


import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

@Service
public class TestService {

	private static final Logger logger = LoggerFactory.getLogger(TestService.class);

	/**
	 * 	4 通过注解的方式定义资源
	 * 注意 blockHandler 函数会在原方法被限流/降级/系统保护的时候调用，
	 *   而 fallback 函数会针对所有类型的异常
	 *   若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑。
	 *   若未配置 blockHandler、fallback 和 defaultFallback，则被限流降级时会将 BlockException 直接抛出（若方法本身未定义 throws BlockException 则会被 JVM 包装一层 UndeclaredThrowableException）。
	 */
	@SentinelResource(value = "resourceName-method3", blockHandler = "methodBlockHandler", fallback = "methodFallback", blockHandlerClass = {TestService.class}, fallbackClass = {TestService.class})
	public String method4(String paramA) throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(100);
		throw new RuntimeException("Err");
	}
	//4-handler 通过注解的方式定义资源
	public String methodBlockHandler(String paramA, BlockException blockException) {
		logger.info("#############Run methodBlockHandler whit paramA = " + paramA);
		blockException.printStackTrace();
		return "Run methodBlockHandler whit paramA = " + paramA;
	}
	//4-fallback 通过注解的方式定义资源
	public String methodFallback(String paramA) {
		logger.info("@@@@@@@@@@methodFallback ");
		return "Run methodFallback whit paramA = " + paramA;
	}

}
