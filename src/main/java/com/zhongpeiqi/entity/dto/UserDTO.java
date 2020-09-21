package com.zhongpeiqi.entity.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class UserDTO implements Serializable {

    private String username;
    private String realName;
    @NotNull(message = "当前页不能为空")
    @Min(1)
    private Integer currentPage;
    @NotNull(message = "页大小不能为空")
    private Integer pageSize;
}
