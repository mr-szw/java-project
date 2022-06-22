package com.dawei.boot.boothelloword.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dawei on 2020/1/14
 */
public class ThreadPoolUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ThreadPoolUtil.class);
	
	private static final Executor executorService =
			new ThreadPoolExecutor(2, 10, 10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10),
					new ThreadPoolExecutor.AbortPolicy());
	
	
	public static <V> V submitTask(TaskSupplier<V> taskSupplier, final String actionMark,
									int runWaitTime, V defaultResult) {
		V v;
		CompletableFuture<V> completableFuture;
		try {
			completableFuture = CompletableFuture.supplyAsync(taskSupplier::get, executorService)
					.whenComplete(ThreadPoolUtil::whenSyncCorrelateComplete);
			v = completableFuture.get(runWaitTime, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			v = defaultResult;
			logger.error("run task {} failed, runWaitTime={}, e=", actionMark , runWaitTime, e);
		}
		return v;
	}

	public static void submitTask(Runnable taskSupplier, final String actionMark) {
		try {
			executorService.execute(taskSupplier);
		} catch (Exception e) {
			logger.error("run task {} failed, e=", actionMark , e);
		}
	}
	
	
	private static <R> void whenSyncCorrelateComplete(R result, Throwable throwable) {
		if (throwable != null) {
			logger.error("Mark exception info, result={} e=", GsonUtil
					.toJson(result), throwable);
		}
	}
}
