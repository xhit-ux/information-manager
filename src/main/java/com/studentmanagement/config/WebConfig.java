package com.studentmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.lang.NonNull;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private InitSystemInterceptor initSystemInterceptor;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(initSystemInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/js/**", "/images/**", "/favicon.ico", "/init_system",
                        "/init_system/**"); // 静态资源放行
    }
}
