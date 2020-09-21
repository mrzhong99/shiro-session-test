package com.zhongpeiqi.service;

import com.zhongpeiqi.common.response.Response;
import com.zhongpeiqi.entity.dto.UserDTO;
import com.zhongpeiqi.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhongpeiqi
 * @since 2020-09-18
 */
public interface UserService extends IService<User> {
    User getByUsername(String username);

    Response getUserPage(UserDTO userDTO);

    Response getUserPageCustom(UserDTO userDTO);
}
