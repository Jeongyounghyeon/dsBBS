    package com.sju.dsBBS.controller;

    import com.sju.dsBBS.dao.UserDao;
    import com.sju.dsBBS.domain.UserDto;
    import com.sju.dsBBS.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    @Controller
    @RequestMapping("/register")
    public class RegisterController {
        @Autowired
        private UserService userService;

        @PostMapping("/save")
        public String save(UserDto userdto, Model m, RedirectAttributes rattr) throws Exception {
            System.out.println("userdto = " + userdto);

            try {                           // exception이 발생하지 않으면, userdto 데이터 저장 후 index page로 이동
                userService.register(userdto);

                rattr.addFlashAttribute("msg", "SAVE_OK");

                return "redirect:/";
            } catch (Exception e) {         // exception이 발생하면, 메세지 출력 후 다시 register화면으로 돌아옴
                e.printStackTrace();
                rattr.addFlashAttribute("msg", "SAVE_ERR");

                return "redirect:/register/add";
            }
        }
    }
