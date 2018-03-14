package com.springboot.core.domain.DO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author yakungao
 * @date 2018/3/14
 **/
@Data
@Entity
@Table(name = "sys_user")
public class SysUserDO implements Serializable {

    private static final long serialVersionUID = 2054548701237642276L;

    @Id
    private int id;
    private String username;
    private String password;
    private Date createTime;
    private Date lastModifyTime;
    private boolean isDel;

}
