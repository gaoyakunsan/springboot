package com.springboot.core.business.service;


import com.springboot.core.business.dao.AdmainDao;
import com.springboot.core.domain.DO.AdmainDO;
import com.springboot.core.domain.query.AdminQueryParams;
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
 * @date 2018/1/10
 **/
@Service
public class AdmainService {

    @Resource
    public AdmainDao admainDao;

    public Page<AdmainDO> list(AdminQueryParams adminQueryParams, int pageNo, int pageSize,
        Sort sort) {

        Specification<AdmainDO> specification = new Specification<AdmainDO>() {
            @Override
            public Predicate toPredicate(Root<AdmainDO> root,
                CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = createPredicate(root, criteriaBuilder, adminQueryParams);
                return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            }

        };
        Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
        return admainDao.findAll(specification, pageable);
    }

    public List<Predicate> createPredicate(Root<AdmainDO> root,CriteriaBuilder criteriaBuilder, AdminQueryParams adminQueryParams){
        List<Predicate> predicates = new ArrayList<Predicate>();
        //参数非空判断。不为空则加此条件
        if (adminQueryParams.getAdminId() != null) {
            Predicate adminId = criteriaBuilder.equal(root.get("adminId"), adminQueryParams.getAdminId());
            predicates.add(adminId);
        }
        return predicates;
    }



}
