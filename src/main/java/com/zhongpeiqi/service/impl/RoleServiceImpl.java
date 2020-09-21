package com.zhongpeiqi.service.impl;

import com.zhongpeiqi.entity.Role;
import com.zhongpeiqi.mapper.RoleMapper;
import com.zhongpeiqi.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhongpeiqi
 * @since 2020-09-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> getByUserId(Integer userId) {
        return baseMapper.getByUserId(userId);
    }

    @Override
    public List<Role> getByUsername(String username) {
        return baseMapper.getByUsername(username);
    }
}
