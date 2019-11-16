package com.dmoffat.dkpmanager.config;

import com.dmoffat.dkpmanager.controller.interceptor.AuthenticationInterceptor;
import com.dmoffat.dkpmanager.controller.interceptor.GuildMasterInterceptor;
import com.dmoffat.dkpmanager.controller.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired private AuthenticationInterceptor authenticationInterceptor;
    @Autowired private GuildMasterInterceptor guildMasterInterceptor;
    @Autowired private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/**.js");
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/dashboard")
                .addPathPatterns("/guild-management/**");
        registry.addInterceptor(guildMasterInterceptor).addPathPatterns("/guild-management/**");
    }
}
