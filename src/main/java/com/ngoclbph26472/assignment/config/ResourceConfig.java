package com.ngoclbph26472.assignment.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ResourceConfig {

    @Bean("messageSource")
    public MessageSource getMessageSource(){
        ReloadableResourceBundleMessageSource ms =
        new ReloadableResourceBundleMessageSource();
        ms.setBasenames("classpath:messages/accounts","classpath:messages/categories","classpath:messages/orders",
                "classpath:messages/ordersdetails","classpath:messages/products");
        ms.setDefaultEncoding("utf-8");
        return ms;
    }
}
