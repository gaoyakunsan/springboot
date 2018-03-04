package com.springboot.core.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

@ApiModel(value="adminQueryParams")
@Data
public class AdminQueryParams {

    @ApiModelProperty(value = "用户ID")
    private Integer adminId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "真实名称")
    private String realName;
    @ApiModelProperty(value = "邮件")
    private String email;
    @ApiModelProperty(value = "电话号码")
    private String phone;
    @NotNull
    private LocalDateTime timeStart;
    @NotNull
    private LocalDateTime timeEnd;

}
