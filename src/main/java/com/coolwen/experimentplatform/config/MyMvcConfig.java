package com.coolwen.experimentplatform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Value("${web.ExpModelImage-path}")
    private String expModelImage ;

    @Value("${web.ExpData-path}")
    private String expData ;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("ExperimentPlatform/ExpModelImage/**").addResourceLocations("file:///"+expModelImage);
        registry.addResourceHandler("ExperimentPlatform/ExpData/**").addResourceLocations("file:///"+expData);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/uploadFile/**").addResourceLocations("file:"+System.getProperty("user.dir")+"\\src\\main\\resources\\static\\uploadFile\\");


//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }

}
