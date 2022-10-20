package com.dongdongshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class MyFreeMarkerConfig {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfig(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setDefaultEncoding("utf-8");
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:freemarker");
        return freeMarkerConfigurer;
    }
}
