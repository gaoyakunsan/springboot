package com.springboot.admin.controler;

import com.springboot.core.business.service.UserService;
import com.springboot.core.domain.DO.SysUserDO;
import com.springboot.core.domain.common.PageArgs;
import com.springboot.core.domain.query.UserQueryParams;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yakungao
 * @date 2018/3/14
 **/
@Controller
public class UserController {

    @Autowired
    public UserService userService;

    //单表查询分页按条件查询
    @RequestMapping(value = "/user")
    public String user(Model model, HttpServletRequest req,
        @Valid UserQueryParams userQueryParams, BindingResult bindingResult,
        @RequestParam(defaultValue = "1") int pageIndex,
        @RequestParam(defaultValue = "3") int pageSize) {
        //AdminQueryParams adminQueryParams = new AdminQueryParams();
        //adminQueryParams.setAdminId(49);
        Sort sort = new Sort(Direction.DESC, "id");
        Page<SysUserDO> page = userService.list(userQueryParams, pageIndex, pageSize, sort);
        /*PageData pageData = new PageData(page.getContent(), pageIndex, pageSize, page.getTotalPages());*/
        PageArgs pageArgs = PaginationUtil
            .genPageArgs(req, page.getTotalElements(), pageSize, pageIndex);
        model.addAttribute("list", page.getContent());
        model.addAttribute("pageArgs", pageArgs);
        model.addAttribute("pageSize", pageSize);
        return "user";
    }
}
