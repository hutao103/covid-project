package com.htht.pro;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.htht.pro.bean.SysUserBean;

/**
 * AdminInterceptor  LoginInterceptor
 * @author Admin
 *
 */
public class AdminInterceptor implements  HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            SysUserBean user=(SysUserBean)request.getSession().getAttribute(Constants.USER_SESSION);
            if(user!=null){
                return true;
            }
            response.sendRedirect(request.getContextPath()+"/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
    
 
}
