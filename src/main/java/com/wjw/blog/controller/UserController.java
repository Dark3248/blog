package com.wjw.blog.controller;

import com.wjw.blog.entity.User;
import com.wjw.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/loginPage")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/registerPage")
    public String toRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, RedirectAttributes attributes) {
        int flag = userService.addUser(user);
        if(flag == 0) {
            attributes.addFlashAttribute("message", "用户名已存在");
            return "redirect:/registerPage";
        } else {
            attributes.addFlashAttribute("message", "注册成功");
            return "redirect:/loginPage";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if(user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/loginPage";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
