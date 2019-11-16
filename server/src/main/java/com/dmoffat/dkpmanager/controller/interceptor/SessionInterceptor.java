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
        Session session;
        if(sessionCookie == null) {
            logger.debug("No session found, creating a new one.");
            session = sessionService.create();
            request.setAttribute("session", session);
        } else {
            logger.debug("Session exists, loading it...");
            session = sessionService.load(sessionCookie);
            request.setAttribute("session", session);
        }

        request.setAttribute("message", session.getMessage());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("SessionInterceptor#postHandle() - Request going out...");
        Session session = (Session)request.getAttribute("session");

        if(session.isChanged()) {
            logger.debug("Session data has changed!");
            Cookie sessionCookie = sessionService.createSessionCookie(session);
            if(sessionCookie != null) {
                logger.debug("Session data write success.");
                response.addCookie(sessionCookie);
            } else {
                logger.error("Failed to write session data.");
            }
        } else {
            logger.debug("The session data has not changed.");
        }

    }
}
