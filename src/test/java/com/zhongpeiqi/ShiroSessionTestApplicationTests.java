package com.zhongpeiqi;

import com.zhongpeiqi.entity.User;
import com.zhongpeiqi.mapper.RoleMapper;
import com.zhongpeiqi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShiroSessionTestApplicationTests {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        System.out.println(roleMapper.getByUserId(1));
    }

    @Test
    void testTime() {
        User user = User.builder()
                .createTime(LocalDateTime.now())
                .password("12313")
                .realName("陈华")
                .salt("2132")
                .updateTime(LocalDateTime.now())
                .username("chenhua")
                .build();
        userService.save(user);
    }
}
