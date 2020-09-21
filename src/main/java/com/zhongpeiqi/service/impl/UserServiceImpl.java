package com.zhongpeiqi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhongpeiqi.common.response.Response;
import com.zhongpeiqi.common.response.ResponseResult;
import com.zhongpeiqi.entity.dto.UserDTO;
import com.zhongpeiqi.entity.User;
import com.zhongpeiqi.entity.vo.UserVo;
import com.zhongpeiqi.mapper.UserMapper;
import com.zhongpeiqi.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhongpeiqi
 * @since 2020-09-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    /**
     * 完全使用mybatis-plus的方法完成分页查询
     * @param userDTO
     * @return
     */
    @Override
    public Response getUserPage(UserDTO userDTO) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StringUtils.isNoneBlank(userDTO.getUsername()),User::getUsername, userDTO.getUsername());
        lambdaQueryWrapper.eq(StringUtils.isNoneBlank(userDTO.getRealName()),User::getRealName, userDTO.getRealName());
        Page<User> userPage = page(new Page<>(userDTO.getCurrentPage(), userDTO.getPageSize()), lambdaQueryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list", userPage.getRecords());
        map.put("total", userPage.getTotal());
        return ResponseResult.SUCCESS(map);
    }

    /**
     * 书写mapper.xml完成分页查询
     * @param userDTO
     * @return
     */
    @Override
    public Response getUserPageCustom(UserDTO userDTO) {
        IPage<UserVo> userIPage = baseMapper.selectPageVo(new Page<>(userDTO.getCurrentPage(), userDTO.getPageSize()), userDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("list", userIPage.getRecords());
        map.put("total", userIPage.getTotal());
        return ResponseResult.SUCCESS(map);
    }
}
