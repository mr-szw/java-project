package com.dawei.boot.boothelloword.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author sinbad on 2021/3/5.
 */
@Configuration
@MapperScan(basePackages = "com.dawei.boot.boothelloword.mapper", sqlSessionFactoryRef = "billSqlSessionFactory")
public class DatasourceConfig {

	@Value("${bill.datasource.url}")
	private String billDatasourceUrl;

	@Value("${bill.datasource.username}")
	private String billDatasourceUserName;

	@Value("${bill.datasource.password}")
	private String billDatasourceUserPassport;

	@Value("${bill.datasource.driverClassName}")
	private String billDatasourceUserDriverClass;

	// 这个暂时不能放到bean中管理。。
	@Bean("billDateSource")
	public DataSource billDateSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(billDatasourceUserDriverClass);
		dataSource.setUrl(billDatasourceUrl);
		dataSource.setUsername(billDatasourceUserName);
		dataSource.setPassword(billDatasourceUserPassport);
		return dataSource;
	}


	/**
	 * 事务管理器 注解@Primary：当程序中使用@Transactional时，优先使用该事务管理器。
	 */
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(
			@Qualifier("billDateSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Primary
	@Bean(name = "billSqlSessionFactory")
	public SqlSessionFactory billSqlSessionFactory(
			@Qualifier("billDateSource") DataSource billShardingJdbcDataSource)
			throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(billShardingJdbcDataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:xmlMapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate getSqlSessionTemplate(
			@Autowired @Qualifier("billSqlSessionFactory") SqlSessionFactory billSqlSessionFactory) {
		return new SqlSessionTemplate(billSqlSessionFactory);
	}
}
