package com.zhongpeiqi.mapper;

import com.zhongpeiqi.entity.Perm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhongpeiqi
 * @since 2020-09-18
 */
public interface PermMapper extends BaseMapper<Perm> {
    List<Perm> getByRoleId(Integer roleId);
    List<Perm> getByUserId(Integer userId);
    List<Perm> getByUsername(String username);
}
