package com.springboot.core.domain.DO;

import java.io.Serializable;
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
@Table(name = "sys_re_role_permission")
public class SysReRolePermission implements Serializable {

    private static final long serialVersionUID = 6055221710795212787L;

    @Id
    private int id;
    private int roleId;
    private int permissionId;
}
