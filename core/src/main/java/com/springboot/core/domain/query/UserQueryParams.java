package com.springboot.core.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author yakungao
 * @date 2018/3/14
 **/
@ApiModel(value="userQueryParams")
@Data
public class UserQueryParams implements Serializable{

    private static final long serialVersionUID = -4402480404154232202L;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @NotNull
    private LocalDateTime timeStart;
    @NotNull
    private LocalDateTime timeEnd;

}
