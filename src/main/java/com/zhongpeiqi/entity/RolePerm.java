package com.zhongpeiqi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhongpeiqi
 * @since 2020-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_role_perm")
public class RolePerm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer roleId;

    private Integer permId;


}
