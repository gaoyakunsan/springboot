package com.springboot.admin.controler;

import com.springboot.admin.model.User;
import com.springboot.core.consts.UserConsts;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yakungao
 * @date 2018/3/8
 **/
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/redirect")
    public String logined(){
        return "index";
    }

    @PostMapping(value = "/login")
    public String loginMethod(HttpServletRequest request, HttpServletResponse response,
        @RequestParam(required = true) String username,
        @RequestParam(required = true) String password) {
        if ("admin".equals(username) && "admin".equals(password)) {
            logger.info("8082");
            User user = new User();
            user.setUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute(UserConsts.SESSION_KEY, user);
            return "index";
        } else {
            return "login";
        }
    }

    @GetMapping(value = "/exit")
    public String exit(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(UserConsts.SESSION_KEY, null);
        return "login";
    }

    @GetMapping(value = "/getSessionUser")
    @ResponseBody
    public String getSessionUser(HttpServletRequest request) {
        String userName = "";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(UserConsts.SESSION_KEY);
        if (user != null) {
            userName = user.getUsername();
        }
        return userName;
    }

}
