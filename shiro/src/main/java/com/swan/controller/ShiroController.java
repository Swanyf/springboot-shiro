package com.swan.controller;

import org.apache.catalina.User;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShiroController {

    @RequestMapping("index")
    public String toIndex() {
        return "user/index";
    }

    @RequestMapping("user/add")
    public String toAdd() {
        return "user/add";
    }

    @RequestMapping("user/update")
    public String toUpdate() {
        return "user/update";
    }

    @RequestMapping("login")
    public String toLogin() {
        return "login";
    }

    /*
        登录逻辑处理
     */
    @RequestMapping("do/login")
    public String doLogin(String username, String password, Model model) {
        // 使用Shiro编写认证操作

        // 获取Subject
        Subject subject = SecurityUtils.getSubject();

        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行登录方法
        try {
            // 登录成功
            subject.login(token);
            return "redirect:/index";
        } catch (UnknownAccountException e) {   // 该异常表示用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {     // 密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }

    }

    /*
        跳转到未授权界面
     */
    @RequestMapping("/unAuth")
    public String toUnAuth() {
        return "unAuth";
    }

}
