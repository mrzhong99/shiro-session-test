package com.zhongpeiqi.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhongpeiqi.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhongpeiqi.entity.dto.UserDTO;
import com.zhongpeiqi.entity.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhongpeiqi
 * @since 2020-09-18
 */
public interface UserMapper extends BaseMapper<User> {
    IPage<UserVo> selectPageVo(Page<?> page, @Param("ud") UserDTO userDTO);
}
