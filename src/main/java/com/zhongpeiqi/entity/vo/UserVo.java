package com.zhongpeiqi.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVo {
    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
