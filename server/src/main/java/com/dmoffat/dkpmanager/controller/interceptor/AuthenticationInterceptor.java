package com.dmoffat.dkpmanager.controller.interceptor;

import com.dmoffat.dkpmanager.model.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(AuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("AuthenticationInterceptor#preHandle()");
        Session session = (Session)request.getAttribute("session");
        if(!session.isLoggedIn()) {
            logger.debug("User is not logged in. Sending to log in page");
            response.sendRedirect("/login");
            return false;
        }

        logger.debug("User is logged in!");
        return true;
    }
}
