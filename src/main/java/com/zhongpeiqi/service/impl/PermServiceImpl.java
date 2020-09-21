package com.zhongpeiqi.service.impl;

import com.zhongpeiqi.entity.Perm;
import com.zhongpeiqi.mapper.PermMapper;
import com.zhongpeiqi.service.PermService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm> implements PermService {

    @Autowired
    private PermMapper permMapper;

    @Override
    public List<Perm> getByUserId(Integer userId) {
        return permMapper.getByUserId(userId);
    }

    @Override
    public List<Perm> getByUsername(String username) {
        return permMapper.getByUsername(username);
    }
}
