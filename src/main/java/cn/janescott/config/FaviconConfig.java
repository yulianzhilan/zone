package cn.janescott.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by scott on 2017/6/7.
 * 更换图标的配置类，由于自动配置图标出现异常
 */
@Configuration
public class FaviconConfig {

    @Bean
    public SimpleUrlHandlerMapping faviconHandlerMapping(){
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        mapping.setUrlMap(Collections.singletonMap("static/favicon.ico",faviconRequestHandler()));
        return mapping;
    }

    @Bean
    protected ResourceHttpRequestHandler faviconRequestHandler(){
        ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
        requestHandler.setLocations(Arrays.asList(new ClassPathResource("/")));
        return requestHandler;
    }
}
