package com.zhongpeiqi.controller;


import com.zhongpeiqi.common.response.Response;
import com.zhongpeiqi.entity.dto.UserDTO;
import com.zhongpeiqi.entity.User;
import com.zhongpeiqi.service.UserService;
import lombok.Value;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhongpeiqi
 * @since 2020-09-18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping
    public User testJsonSave(@RequestBody User user) {
        userService.save(user);
        System.out.println(user);
        return user;
    }

    @PostMapping("/list")
    public Response getPageUser(@RequestBody @Validated UserDTO userDTO) {
        return userService.getUserPage(userDTO);
    }

    @PostMapping("/list_custom")
    @RequiresRoles("admin")
    public Response getPageUserCustom(@RequestBody @Validated UserDTO userDTO) {
       return userService.getUserPageCustom(userDTO);
    }
}

