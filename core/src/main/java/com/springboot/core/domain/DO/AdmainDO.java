package com.springboot.core.domain.DO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author yakungao
 * @date 2018/1/10
 **/
@Data
@Entity
@Table(name="admin")
public class AdmainDO implements Serializable {

    private static final long serialVersionUID = 5140596244912667656L;

    @Id
    private  Integer adminId;

    private  String username;

    private  String password;

    private  String realname;

    private  String email;

    private  String phone;

    private  boolean isdelete;

    private Date lastModifiedTime;

    private Date createTime;
    private Integer adminPid;

}
