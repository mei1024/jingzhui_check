package com.solar.mb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.lark",
		"com.solar.mb","com.solar.ms","com.solar.acc","com.solar.pay","com.solar.app","com.solar.job","com.solar.uc","com.solar.cms","com.solar.bi",
		"com.solar.mas","com.solar.ds","com.solar.auth","com.solar.common","com.solar.security","com.nebula.sequence","com.nebula.common.web.rest"
		})
public class JZhApplication extends SpringBootServletInitializer {
 
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JZhApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(JZhApplication.class, args);
    }
}