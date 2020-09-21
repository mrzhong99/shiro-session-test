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
@TableName("tbl_perm")
public class Perm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名
     */
    private String permName;

    /**
     * 父权限id，无父权限为0
     */
    private Integer parentId;


}
