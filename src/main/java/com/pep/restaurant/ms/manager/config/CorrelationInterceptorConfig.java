package com.pep.restaurant.ms.manager.config;

import com.pep.restaurant.ms.manager.logging.CorrelationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorrelationInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    public CorrelationInterceptor correlationInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(correlationInterceptor);
    }

}
