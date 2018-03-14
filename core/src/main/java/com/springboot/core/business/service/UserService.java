package com.springboot.core.business.service;

import com.springboot.core.business.dao.UserDao;
import com.springboot.core.domain.DO.AdmainDO;
import com.springboot.core.domain.DO.SysUserDO;
import com.springboot.core.domain.query.AdminQueryParams;
import com.springboot.core.domain.query.UserQueryParams;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author yakungao
 * @date 2018/3/14
 **/
@Service
public class UserService {

    @Resource
    public UserDao userDao;

    public Page<SysUserDO> list(UserQueryParams userQueryParams, int pageNo, int pageSize,
        Sort sort) {

        Specification<SysUserDO> specification = new Specification<SysUserDO>() {
            @Override
            public Predicate toPredicate(Root<SysUserDO> root,
                CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = createPredicate(root, criteriaBuilder, userQueryParams);
                return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            }

        };
        Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
        return userDao.findAll(specification, pageable);
    }

    public List<Predicate> createPredicate(Root<SysUserDO> root,CriteriaBuilder criteriaBuilder, UserQueryParams userQueryParams){
        List<Predicate> predicates = new ArrayList<Predicate>();
        //参数非空判断。不为空则加此条件
        if (userQueryParams.getUserId() != null) {
            Predicate userId = criteriaBuilder.equal(root.get("id"), userQueryParams.getUserId());
            predicates.add(userId);
        }
        Predicate isDel = criteriaBuilder.equal(root.get("isDel"), false);
        predicates.add(isDel);
        return predicates;
    }

}
