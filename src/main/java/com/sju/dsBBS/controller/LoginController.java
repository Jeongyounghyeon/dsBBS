package com.sju.dsBBS.controller;

import com.sju.dsBBS.dao.UserDao;
import com.sju.dsBBS.domain.SessionDto;
import com.sju.dsBBS.domain.UserDto;
import com.sju.dsBBS.service.SessionService;
import com.sju.dsBBS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.*;
import java.util.Calendar;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String id, String pwd, boolean rememberMe, RedirectAttributes rattr,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 아이디와 패스워드 확인
            userService.login(id, pwd);

            // 아이디와 패스워드가 일치하면 세션 객체를 얻어와서 id 저장
            HttpSession session = request.getSession();
            session.setAttribute("id", id);

            // rememberMe가 체크면 cookie 생성 후 일주일 설정
            if (rememberMe) {
                SessionDto sessionDto = new SessionDto();

                Cookie cookie = new Cookie("autoLoginBySessionId", session.getId());

                // 쿠키를 찾을 경로를 컨텍스트 경로로 변경
                cookie.setPath("/");
                cookie.setMaxAge(60*60*24*7);
                response.addCookie(cookie);

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE,7);

                sessionDto.setSessionId(session.getId());
                sessionDto.setSessionLimit(calendar.getTime());
                sessionDto.setUserId(id);

                sessionService.addUpdateSessionData(sessionDto);

            }

            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "LOG_ERR");

            return "redirect:/login/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
//        // 1. 세션을 종료
//        session.invalidate();
        String userId = (String)session.getAttribute("id");
        if(userId != null) {
            // session 종료
            session.removeAttribute("id");
            session.invalidate();
    
            Cookie cookie = WebUtils.getCookie(request, "autoLoginBySessionId");
            if(cookie != null) {
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);

                // session data 삭제(sessionService이용)
                String sessionId = cookie.getValue();
                try {
                    sessionService.remove(sessionId);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }

//        // 2. 홈으로 이동
        return "redirect:/";
    }
}
