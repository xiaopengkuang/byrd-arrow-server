package com.arrow.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.Driver;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

/**
 * DESCRIPTION:
 * Created by BYRD on 26/10/2017
 * Version 0.1
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public MyBatisConfig() {
        logger.info("init mybatis...");
    }

    @Bean(name = "resourceLoader")
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }

    @Bean(name = "resourcePatternResolver")
    public ResourcePatternResolver resourcePatternResolver() {
        return new PathMatchingResourcePatternResolver();
    }

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource(Environment environment) throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(environment.getProperty("jdbc.datasource.username"));
        dataSource.setPassword(environment.getProperty("jdbc.datasource.password"));
        dataSource.setUrl(environment.getProperty("jdbc.datasource.url"));
        dataSource.setValidationQuery(environment.getProperty("jdbc.validateQuery"));
        druidSettings(dataSource);

        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(Environment environment,
                                                       DruidDataSource dataSource,
                                                       ResourceLoader resourceLoader,
                                                       ResourcePatternResolver resourcePatternResolver) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(environment.getProperty("mybatis.sqlMapper-locations")));
        if (environment.getProperty("mybatis.config-location") != null) {
            sqlSessionFactoryBean.setConfigLocation(resourceLoader.getResource(environment.getProperty("mybatis.config-location")));
        }

        sqlSessionFactoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.type-aliases-package"));

        return sqlSessionFactoryBean;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(DruidDataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);

        return dataSourceTransactionManager;
    }

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment) {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage(environment.getProperty("mybatis.sqlMapper.base.package"));
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");

        return mapperScannerConfigurer;
    }

    private void druidSettings(DruidDataSource druidDataSource) throws Exception {
        druidDataSource.setMaxActive(1000);
        druidDataSource.setInitialSize(0);
        druidDataSource.setMinIdle(0);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(100);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTimeBetweenEvictionRunsMillis(6000);
        druidDataSource.setMinEvictableIdleTimeMillis(2520000);
        druidDataSource.setRemoveAbandoned(true);
        druidDataSource.setRemoveAbandonedTimeout(18000);
        druidDataSource.setLogAbandoned(true);
        druidDataSource.setFilters("mergeStat");
        druidDataSource.setDriver(new Driver());
    }
}
