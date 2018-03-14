package com.springboot.core.business.dao;

import com.springboot.core.domain.DO.SysUserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author yakungao
 * @date 2018/3/14
 **/
@Repository
public interface UserDao extends JpaRepository<SysUserDO, Integer>, JpaSpecificationExecutor<SysUserDO> {

}
