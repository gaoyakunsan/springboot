package com.springboot.admin.config;

import com.springboot.core.consts.UserConsts;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * @author yakungao
 * @date 2018/3/8
 **/
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

    String loginUrl = "/login";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry
            .addInterceptor(getSecurityInterceptor());
        //不需要过滤的url
        interceptorRegistration.excludePathPatterns("/error", "/login/**", "/static/**");
        //需要过滤的url
        interceptorRegistration.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
            HttpSession session = request.getSession();
            if (session.getAttribute(UserConsts.SESSION_KEY) == null) {
                response.sendRedirect(loginUrl);
                return false;
            }
            return true;
        }
    }
}
