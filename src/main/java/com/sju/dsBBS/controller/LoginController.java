package com.sju.dsBBS.controller;

import com.sju.dsBBS.dao.UserDao;
import com.sju.dsBBS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String id, String pwd, boolean rememberMe, RedirectAttributes rattr,
                        HttpServletRequest request) throws Exception {
        try {
            // 아이디와 패스워드 확인
            userService.login(id, pwd);

            // 아이디와 패스워드가 일치하면 세션 객체를 얻어와서 id 저장
            HttpSession session = request.getSession();
            session.setAttribute("id", id);

//            // rememberMe가 체크면 session 일주일 설정
//            if(rememberMe)
//                System.out.println(rememberMe);
//                session.setMaxInactiveInterval(60*60*24*7);

            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "LOG_ERR");

            return "redirect:/login/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 1. 세션을 종료
        session.invalidate();
        // 2. 홈으로 이동
        return "redirect:/";
    }
}
