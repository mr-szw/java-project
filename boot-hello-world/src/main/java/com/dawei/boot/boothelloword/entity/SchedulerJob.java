package com.dawei.boot.boothelloword.entity;

import java.io.Serializable;

/**
 * @author by Dawei on 2019/3/30.
 */
public class SchedulerJob  {

    private String jobId;
    //任务执行集群
    private String clusterName;
    //声明任务名
    private String jobName;
    //执行类名
    private String jobClassName;
    //执行方法名
    private String jobMethodName;
    //方法参数名 仅限一个值
    private String methodParamName;
    //方法参数值 仅限一个值
    private String methodParamValue;
    //任务执行时间
    private String cronStr;
    //任务状态
    private Integer status;
    //负责人邮箱
    private String welfareEmail;
    //描述信息
    private String description;


}

