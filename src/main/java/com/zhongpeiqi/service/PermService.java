package com.zhongpeiqi.service;

import com.zhongpeiqi.entity.Perm;
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
public interface PermService extends IService<Perm> {
    List<Perm> getByUserId(Integer userId);
    List<Perm> getByUsername(String username);
}
