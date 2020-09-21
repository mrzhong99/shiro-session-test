package com.zhongpeiqi.service;

import com.zhongpeiqi.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhongpeiqi
 * @since 2020-09-18
 */
public interface RoleService extends IService<Role> {
    List<Role> getByUserId(Integer userId);
    List<Role> getByUsername(String username);
}
