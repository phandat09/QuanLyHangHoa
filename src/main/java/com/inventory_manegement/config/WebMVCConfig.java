package com.inventory_manegement.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.inventory_manegement.filter.CustomURLFilter;


// đánh dấu để Spring boot biết file j để cấu hình
@Configuration
// đánh đấu để spring-boot bật modul mvc cho web
@EnableWebMvc
// đánh dấu để spring boot biết chỗ để tìm  COntroller
@ComponentScan(basePackages = {"com.inventory_manegement.contronller"})
public class WebMVCConfig implements WebMvcConfigurer {
	/**
	 * chỗ đặt file jsp 
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/jsp/");
		bean.setSuffix(".jsp");
		return bean;
	}
	/**
	 * mapping js,img,css
	 * classPath là thư mục src/main/resources
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// Register resource handler for CSS
		// Register resource handler for CSS out file: link thư mục bên ngoài
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/resources/css/");
			//	.setCacheControl(CacheControl.maxAge(2, TimeUnit.SECONDS).cachePublic());

		// Register resource handler for JavaScript
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/resources/js/");
		//		.setCacheControl(CacheControl.maxAge(2, TimeUnit.SECONDS).cachePublic());

		// Register resource handler for images
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/resources/images/");
			//	.setCacheControl(CacheControl.maxAge(2, TimeUnit.SECONDS).cachePublic());
		// Register resource handler for fonts
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/resources/webfonts/");
		//.setCacheControl(CacheControl.maxAge(2, TimeUnit.SECONDS).cachePublic());
		registry.addResourceHandler("/glyphicons/**").addResourceLocations("classpath:/resources/glyphicons/");
		//.setCacheControl(CacheControl.maxAge(2, TimeUnit.SECONDS).cachePublic());
		registry.addResourceHandler("/files/**").addResourceLocations("file:E:/java/WorkSpace/inventor_manegement/inventor_manegament/inventory_manegement/upload/");
		
	}
	@Bean
	public FilterRegistrationBean<SiteMeshConfig> siteMeshFilter() {
		FilterRegistrationBean<SiteMeshConfig> fitler = new FilterRegistrationBean<SiteMeshConfig>();
		fitler.setFilter(new SiteMeshConfig());
		fitler.addUrlPatterns("/*");
		
		return fitler;
	}
	
	  @Bean public MultipartResolver multipartResolver() { CommonsMultipartResolver
	  commonsMultipartResolver = new CommonsMultipartResolver();
	  commonsMultipartResolver.setMaxUploadSize(3000000); return
	  commonsMultipartResolver; }
	 
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	    mailSender.setHost("smtp.gmail.com");
//	    mailSender.setPort(587);
//	     
//	    mailSender.setUsername("phandathumg61@gmail.com");
//	    mailSender.setPassword("phantuandat");
//	     
//	    Properties props = mailSender.getJavaMailProperties();
//	    props.put("mail.transport.protocol", "smtp");
//	    props.put("mail.smtp.auth", "true");
//	    props.put("mail.smtp.starttls.enable", "true");
//	    props.put("mail.debug", "true");
//	     
//	    return mailSender;
//	}
	@Bean
	public FilterRegistrationBean<CustomURLFilter> filterCustomURLFilter() {
		FilterRegistrationBean<CustomURLFilter> registrationBean = new FilterRegistrationBean<CustomURLFilter>();
		registrationBean.setFilter(new CustomURLFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(2);
		return registrationBean;
	}

}
