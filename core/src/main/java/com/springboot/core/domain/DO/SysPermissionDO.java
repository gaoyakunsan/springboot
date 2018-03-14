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
@Table(name = "sys_permission")
public class SysPermissionDO implements Serializable {

    private static final long serialVersionUID = 5027781776750946158L;

    @Id
    private int id;
    private int parentId;
    private String name;
    private String url;
    private int sort;
    private boolean isMenu;
    private boolean isEnable;
    private String icon;
    private Date createTime;
    private Date lastModifyTime;
    private boolean isDel;
}
