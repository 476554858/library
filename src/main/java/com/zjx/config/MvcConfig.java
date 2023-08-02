package com.zjx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private LibraryInterceptor libraryInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(libraryInterceptor)
                .excludePathPatterns("/login", "/regist", "/user/login", "/user/add")
                .addPathPatterns("/**");
    }
}
