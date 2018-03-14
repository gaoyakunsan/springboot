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
@Table(name = "sys_role")
public class SysRoleDO implements Serializable {

    private static final long serialVersionUID = 3992513817282272787L;

    @Id
    private int id;
    private String name;
    private int sort;
    private Date createTime;
    private Date lastModifyTime;
    private boolean isDel;
}
