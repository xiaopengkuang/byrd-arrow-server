package com.arrow.user.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DESCRIPTION:
 * Created by BYRD on 25/10/2017
 * Version 0.1
 */
@Configuration
@ComponentScan("com.arrow.user")
@SpringBootConfiguration
@EnableTransactionManagement
public class GlobalConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Environment environment;

    public GlobalConfig() {
        logger.info("starting spring container...");
    }

    public String getProperty(String key) {
        return environment.getProperty(key);
    }

    @Configuration
    @PropertySource(value = "classpath:profile/dev/application.properties", encoding = "utf-8")
    @Profile("dev")
    public class DevPropertyConfig {
    }

    @Configuration
    @PropertySource(value = "classpath:profile/pro/application.properties", encoding = "utf-8")
    @Profile("pro")
    public class ProPropertyConfig {
    }
}
