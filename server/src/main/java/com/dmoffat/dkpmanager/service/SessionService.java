package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.model.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.UUID;

/**
 * TODO:
 * - Sign cookies so they can't be tampered with (i.e. stop someone impersonating someone else)
 */
@Service
public class SessionService {

    @Autowired private ObjectMapper objectMapper;
    @Autowired private PlayerService playerService;

    public String getCookieLabel() {
        return "SESSION";
    }

    public Session create() {
        Session session = new Session();
        session.setId(UUID.randomUUID().toString());
        return session;
    }

    public Session load(Cookie cookie) {
        String sessionData = cookie.getValue();
        try {
            String decoded = new String(Base64Utils.decodeFromUrlSafeString(sessionData));
            Session session = objectMapper.readValue(decoded, Session.class);
            if(session.getPlayerId() != null) {
                session.setPlayer(playerService.findById(session.getPlayerId()));
            }
            return session;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String serialise(Session session) {
        try {
            String jsonStr = objectMapper.writeValueAsString(session);
            System.out.println(jsonStr);
            return Base64Utils.encodeToUrlSafeString(jsonStr.getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cookie createSessionCookie(Session session) {
        Cookie cookie = new Cookie(getCookieLabel(), serialise(session));
        cookie.setMaxAge(60 * 60 * 60 * 24);
        cookie.setPath("/");
        cookie.setDomain("");
        return cookie;
    }

}
