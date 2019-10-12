package com.qf.controller;


import com.qf.domain.SysUser;
import com.qf.service.SysUserService;
import com.qf.utils.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    //管理员注册
    @RequestMapping("/sysuserRegist")
    public Integer sysuserRegist(@RequestBody SysUser sysUser) {
        if (sysUserService.findByUsername(sysUser.getUsername()) == null) {
            String password = Md5Utils.getMd5Password(sysUser.getPassword());
            sysUser.setPassword(password);
            sysUserService.sysuserRegisr(sysUser);
            return 1;
        } else {
            return 0;
        }
    }


    @RequestMapping("/sysuserlogin")
    public Integer sysuserlogin(HttpSession session, @RequestBody SysUser sysUser) {
        SysUser user = sysUserService.findByUsername(sysUser.getUsername());
        if (user != null) {
            String password = Md5Utils.getMd5Password(sysUser.getPassword());
            if (user.getPassword().equals(password)) {

                SysUser sysUser1 = sysUserService.findByUsername(sysUser.getUsername());
                session.setAttribute("sysuserId",sysUser1.getUserid());
                return 1;
            }else {
                return 0;
            }
        }
        return 0;
    }


    //管理员个人信息查询
    @RequestMapping("/getMsgSysUser")
    public SysUser getMsgSysUser(HttpSession session){
        Integer userid = (Integer)session.getAttribute("sysuserId");
        SysUser sysUser = sysUserService.findByUserId(userid);
        System.out.println(sysUser);
        return sysUser;
    }

    //管理员个人信息修改
    @RequestMapping("/updateSysUser")

    public Integer updateSysUser(@RequestBody SysUser sysUser){

        sysUserService.updateSysUser(sysUser);
        return 1;
    }

    //管理员登陆注销
    @RequestMapping("/SysUserLoginOut")

    public Integer SysUserLoginOut(HttpSession session){
       session.invalidate();

        return 1;
    }

}
