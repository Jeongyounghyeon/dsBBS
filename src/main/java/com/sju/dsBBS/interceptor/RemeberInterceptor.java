package com.sju.dsBBS.interceptor;

import com.sju.dsBBS.domain.UserDto;
import com.sju.dsBBS.service.SessionService;
import com.sju.dsBBS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemeberInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie sessionId = WebUtils.getCookie(request, "autoLoginBySessionId");
        // sessionId가 Cookie에 있으면
        if (sessionId != null) {
            sessionId.setPath("/");
            sessionId.setMaxAge(60*60*24*7);
            response.addCookie(sessionId);
            
            String userId = sessionService.checkSessionAndUpdate(sessionId.getValue());
//            System.out.println("userId = " + userId);
            if(userId != null) {
                HttpSession session = request.getSession();
                session.setAttribute("id", userId);
            }
        }

        return true;
    }
}
