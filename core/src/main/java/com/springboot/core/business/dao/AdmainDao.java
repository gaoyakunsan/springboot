package com.springboot.core.business.dao;


import com.springboot.core.domain.DO.AdmainDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author yakungao
 * @date 2018/1/10
 **/
@Repository
public interface AdmainDao extends JpaRepository<AdmainDO, Integer>,JpaSpecificationExecutor<AdmainDO> {

}
