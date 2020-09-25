package com.micwsx.project.controller;

import com.micwsx.project.JwtUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 模拟登录认证服务，颁布token
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @RequestMapping("/accesstoken/{userId}")
    public String accesstoken(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response){
        // 假设登录成功直接返回用户token
        String token = JwtUtil.generateToken(userId);
        System.out.println(token);
        return token;
    }

    @RequestMapping("/logout/{userId}")
    public String logout(@PathVariable String userId,HttpServletRequest request, HttpServletResponse response){
        return userId+"已成功Logout";
    }
}
