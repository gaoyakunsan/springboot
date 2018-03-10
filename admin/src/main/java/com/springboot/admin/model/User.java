package com.springboot.admin.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author yakungao
 * @date 2018/3/8
 **/
@Data
public class User implements Serializable{

    private static final long serialVersionUID = 8213622873644523344L;
    private int id;
    private String username;

}
