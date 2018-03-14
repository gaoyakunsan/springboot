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
@Table(name = "sys_re_user_role")
public class SysReUserRole implements Serializable {

    private static final long serialVersionUID = -2578373380030660218L;

    @Id
    private int id;
    private int userId;
    private int roleId;
}
