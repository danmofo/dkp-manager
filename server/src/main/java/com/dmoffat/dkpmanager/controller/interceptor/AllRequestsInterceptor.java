package com.dmoffat.dkpmanager.controller.interceptor;

import com.dmoffat.dkpmanager.util.TimeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AllRequestsInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(AllRequestsInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug(request.getMethod() + " => " + request.getRequestURI());
        request.setAttribute("timeUtils", new TimeUtils());
        return true;
    }
}
