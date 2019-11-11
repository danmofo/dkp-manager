package com.dmoffat.dkpmanager.controller.interceptor;

import com.dmoffat.dkpmanager.model.Session;
import com.dmoffat.dkpmanager.service.SessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter  {

    private static final Logger logger = LogManager.getLogger(SessionInterceptor.class);

    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("SessionInterceptor#preHandle() - Request coming in..." + request.getRequestURI());

        Cookie sessionCookie = WebUtils.getCookie(request, sessionService.getCookieLabel());
        if(sessionCookie == null) {
            logger.debug("No session found, creating a new one.");
            request.setAttribute("session", sessionService.create());
        } else {
            logger.debug("Session exists, loading it...");
            request.setAttribute("session", sessionService.load(sessionCookie));
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("SessionInterceptor#afterCompletion() - Request going out...");
        Session session = (Session)request.getAttribute("session");
        Cookie sessionCookie = sessionService.createSessionCookie(session);
        if(sessionCookie != null) {
            logger.debug("Session data write success.");
            response.addCookie(sessionCookie);
        } else {
            logger.error("Failed to write session data.");
        }
    }
}
