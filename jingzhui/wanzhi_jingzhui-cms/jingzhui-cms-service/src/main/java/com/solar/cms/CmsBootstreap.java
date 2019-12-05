package com.solar.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.solar.cms","com.solar.ds","com.solar.auth","com.solar.common","com.nebula.sequence","com.nebula.common.web.rest"})
public class CmsBootstreap {
 
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CmsBootstreap.class);
        application.run(args);
    }
 
}