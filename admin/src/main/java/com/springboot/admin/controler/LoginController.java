package com.springboot.admin.controler;

import com.mysql.jdbc.util.ServerController;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String logined(Model model, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(UserConsts.SESSION_KEY);
        model.addAttribute("username", user.getUsername());
        return "index";
    }

    @PostMapping(value = "/login")
    public String loginMethod(HttpServletRequest request, HttpServletResponse response,
        @RequestParam(required = true) String username,
        @RequestParam(required = true) String password) {
        if ("admin".equals(username) && "admin".equals(password)) {
            User user = new User();
            user.setUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute(UserConsts.SESSION_KEY, user);
            return "redirect:/redirect";
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

}
