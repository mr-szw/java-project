package com.dawei.boot.boothelloword.aspect;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author by Dawei on 2019/3/12.
 *
 * 切面操作
 */
@Configuration
@Aspect
public class AopConfigHandler {

    private static final Logger logger = LoggerFactory.getLogger(AopConfigHandler.class);


    /**
     * 对指定注解做切点
     * 对应注解:
     *      @see com.dawei.boot.boothelloword.annotation.OperationCutPoint
     * @param proceedingJoinPoint 进程切点
     * @return 返回操作结果
     * @throws Throwable 异常不做处理直接抛出
     */
    @Around("@within(com.dawei.boot.boothelloword.annotation.OperationCutPoint)")
    public Object doRecordLog(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        logger.info("doRecordLog >>>>>>>>> args={}", Arrays.toString(args));
        try {
            Object proceed = proceedingJoinPoint.proceed();
            logger.info("doRecordLog >>>>>>>>> result={}", proceed);
            return  proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }


    }






}
