package com.dawei.boot.boothelloword.redis;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sinbad on 2021/3/11.
 */
@Slf4j
@Configuration
public class RedissonAutoConfiguration {

	@Bean("clusterServersConfig")
	@ConditionalOnMissingBean
	public Config clusterServersConfig() {
		Config config = new Config();
		ClusterServersConfig clusterServersConfig = config.useClusterServers();
		// 集群扫描间隔时间
		clusterServersConfig.setScanInterval(4000).setCheckSlotsCoverage(true).addNodeAddress("")
				.setPassword("")
				// 同任何节点建立连接时的等待超时
				.setConnectTimeout(1000)
				// 命令等待超时
				.setTimeout(500)
				//
				.setMasterConnectionPoolSize(10).setMasterConnectionMinimumIdleSize(2)
				.setSlaveConnectionPoolSize(10).setSlaveConnectionMinimumIdleSize(2)
				.setIdleConnectionTimeout(1000);
		return config;
	}

	@Bean(destroyMethod = "shutdown")
	@ConditionalOnMissingBean
	public RedissonClient redissonClient(Config config) throws IOException {
		log.info("create RedissonClient, config is : {}", config.toYAML());
		return Redisson.create(config);
	}

}
