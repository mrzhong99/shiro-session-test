package com.zhongpeiqi.mapper;

import com.zhongpeiqi.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhongpeiqi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhongpeiqi
 * @since 2020-09-18
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getByUserId(Integer userId);
    List<Role> getByUsername(String username);
}
