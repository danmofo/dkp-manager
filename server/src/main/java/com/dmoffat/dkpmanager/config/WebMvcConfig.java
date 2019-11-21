package com.dmoffat.dkpmanager.config;

import com.dmoffat.dkpmanager.controller.interceptor.AllRequestsInterceptor;
import com.dmoffat.dkpmanager.controller.interceptor.AuthenticationInterceptor;
import com.dmoffat.dkpmanager.controller.interceptor.GuildManagementInterceptor;
import com.dmoffat.dkpmanager.controller.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired private AllRequestsInterceptor allRequestsInterceptor;
    @Autowired private AuthenticationInterceptor authenticationInterceptor;
    @Autowired private GuildManagementInterceptor guildManagementInterceptor;
    @Autowired private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(allRequestsInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**.js");

        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**.js");

        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/dashboard")
                .addPathPatterns("/guild-management/**");

        registry.addInterceptor(guildManagementInterceptor)
                .addPathPatterns("/guild-management/**");
    }
}
