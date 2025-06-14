package com.koreait.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableScheduling
@EnableWebMvc
@ComponentScan(basePackages = { "com.koreait.www.controller", "com.koreait.www.service", "com.koreait.www.handler", "com.koreait.www.security", "com.koreait.www.exception" })
// DAO는 DB에서 설정, 나머지는 @ComponentScan에 추가
public class ServletConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// resources 경로 설정 / 나중에 파일 업로드에 대한 경로 설정 추가
		registry.addResourceHandler("/resources/**") // 내가 코드에서 밑 경로를 부를 때 사용할 이름
				.addResourceLocations("/resources/"); // 실제 경로
		registry.addResourceHandler("/upload/**")
				.addResourceLocations("file:///D:\\web_0226_lyh\\_myProject\\_java\\_fileUpload\\");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// InternalResourceViewResolver
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		
		registry.viewResolver(viewResolver);
	}
	
	// 파일 업로드 resolver 추가
	// Bean 이름은 multipartResolver여야 함
	@Bean(name = "multipartResolver")
	public MultipartResolver getMultipartResolver() {
		return new StandardServletMultipartResolver();
	}
}
