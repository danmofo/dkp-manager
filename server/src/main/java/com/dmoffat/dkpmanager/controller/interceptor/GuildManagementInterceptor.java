package com.dmoffat.dkpmanager.controller.interceptor;

import com.dmoffat.dkpmanager.model.Player;
import com.dmoffat.dkpmanager.model.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GuildManagementInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(GuildManagementInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("GuildMasterInterceptor#preHandle()");
        Session session = (Session)request.getAttribute("session");
        Player player = session.getPlayer();
        if(player == null || !player.getIsGuildMaster()) {
            response.sendRedirect("/dashboard");
            return false;
        }

        request.setAttribute("guild", player.getGuild());

        return true;
    }

}
