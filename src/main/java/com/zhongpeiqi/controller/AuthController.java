package com.zhongpeiqi.controller;

import com.zhongpeiqi.common.code.CommonCode;
import com.zhongpeiqi.common.response.Response;
import com.zhongpeiqi.common.response.ResponseResult;
import com.zhongpeiqi.entity.dto.LoginDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
public class AuthController {

    @PostMapping("/login")
    public Response login(@RequestBody LoginDTO loginDTO) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(loginDTO.getUsername(), loginDTO.getPassword()));
        return ResponseResult.SUCCESS();
    }

    @GetMapping("/user_info")
    public Response userInfo() {
        Map<String, Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        Collection<Object> sessionAttributeKeys = subject.getSession().getAttributeKeys();
        Object authenticatedSession = subject.getSession().getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_AUTHENTICATED_SESSION_KEY");
        Object hostSession = subject.getSession().getAttribute("org.apache.shiro.web.session.HttpServletSession.HOST_SESSION_KEY");
        Object principalsSession = subject.getSession().getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
        Serializable sessionId = subject.getSession().getId();
        map.put("username", principal);
        map.put("sessionAttributeKeys", sessionAttributeKeys);
        map.put("authenticatedSession", authenticatedSession);
        map.put("hostSession", hostSession);
        map.put("principalsSession", principalsSession);
        map.put("sessionId", sessionId);
        return ResponseResult.SUCCESS(map);
    }

    @GetMapping("/notLogin")
    public Response notLogin() {
        return ResponseResult.FAIL(CommonCode.NOT_LOGIN);
    }
}
