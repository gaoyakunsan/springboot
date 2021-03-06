package com.springboot.admin.controler;


import com.springboot.core.business.service.AdmainService;
import com.springboot.core.domain.DO.AdmainDO;
import com.springboot.core.domain.common.PageArgs;
import com.springboot.core.domain.common.PageData;
import com.springboot.core.domain.query.AdminQueryParams;
import com.springboot.core.util.PaginationUtil;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yakungao
 * @date 2018/1/9
 **/
@Controller
public class TestController {

    @Autowired
    public AdmainService admainService;

    @GetMapping(value = "/index")
    public String Test() {
        //List<AdmainDO> list = admainService.findAllAdmain();
        System.out.println("dsafdsf");

        return "index";
    }

    //单表查询分页按条件查询
    @GetMapping(value = "/admin")
    public String admain(Model model, HttpServletRequest req,
        @Valid AdminQueryParams adminQueryParams, BindingResult bindingResult,
        @RequestParam(defaultValue = "1") int pageIndex,
        @RequestParam(defaultValue = "3") int pageSize) {
        //AdminQueryParams adminQueryParams = new AdminQueryParams();
        //adminQueryParams.setAdminId(49);
        Sort sort = new Sort(Direction.DESC, "adminId");
        Page<AdmainDO> page = admainService.list(adminQueryParams, pageIndex, pageSize, sort);
        /*PageData pageData = new PageData(page.getContent(), pageIndex, pageSize, page.getTotalPages());*/
        PageArgs pageArgs = PaginationUtil.genPageArgs(req, page.getTotalElements(), pageSize, pageIndex);
        model.addAttribute("list", page.getContent());
        model.addAttribute("pageArgs", pageArgs);
        model.addAttribute("pageSize", pageSize);
        return "admin";
    }
    //多表分页按条件查询

}
