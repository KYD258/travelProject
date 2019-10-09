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
    public Integer sysuserlogin(@RequestBody SysUser sysUser) {
        SysUser user = sysUserService.findByUsername(sysUser.getUsername());
        if (user != null) {
            String password = Md5Utils.getMd5Password(sysUser.getPassword());
            if (user.getPassword().equals(password)) {
                return 1;
            }else {
                return 0;
            }
        }
        return 0;
    }

}
