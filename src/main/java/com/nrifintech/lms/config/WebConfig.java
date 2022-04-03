package com.nrifintech.lms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nrifintech.lms.interceptor.Interceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	Interceptor interceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) 
	{
		System.out.println("Config");
		registry.addInterceptor(interceptor).excludePathPatterns("/css/**", "/js/**", "/assets/**", "/catalogue/*");
	}
}
