package com.zhj.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;
import com.zhj.constants.MsgCodeConstants;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhj.service.LoginLogService;
import com.zhj.service.LoginService;
import com.zhj.vo.LoginLogVo;
import com.zhj.vo.User;

public class LoginAction extends ActionSupport {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginLogService loginLogService;
    private Map<String, String> resultMap;
    private User user;

    /**
     * 用户登录
     */
    public String login() {
        Map<String, Object> userMap = loginService.login(user);
        if (MsgCodeConstants.SUCCESS_CODE.equals(userMap.get("code"))) {
            user = (User) userMap.get("currentUser");
            HttpServletRequest request = ServletActionContext.getRequest();
            LoginLogVo vo = new LoginLogVo();
            vo.setUserId(user.getId());
            vo.setLoginIp(request.getRemoteAddr());
            loginLogService.addUserLoginLog(vo);
            request.getSession().setAttribute("currentUser", user);
        }
        resultMap = resultMap == null ? new HashMap<String, String>() : resultMap;
        resultMap.put("code", (String) userMap.get("code"));
        resultMap.put("msg", (String) userMap.get("msg"));
        return SUCCESS;
    }

    public String register() {
        return "system/register";
    }

    /**
     * 新用户注册
     */
    public String doRegister() {
        Map<String, String> resMap = loginService.insertUser(user);
        return SUCCESS;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
