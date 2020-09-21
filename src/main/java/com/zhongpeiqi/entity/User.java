package com.zhongpeiqi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhongpeiqi
 * @since 2020-09-18
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    /**
     * 更新时间
     */

    private LocalDateTime updateTime;


}
