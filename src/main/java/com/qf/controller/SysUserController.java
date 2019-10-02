package com.qf.controller;


import com.qf.domain.SysUser;
import com.qf.service.SysUserService;
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
            String byUsername = sysUserService.findByUsername(sysUser.getUsername());

            if (byUsername!=null){
            if (!byUsername.equals(sysUser.getUsername())) {
                String hashAlgorithName = "MD5";//加密算法
                String password = sysUser.getPassword();//登陆时的密码
                int hashIterations = 1024;//加密次数
                ByteSource credentialsSalt = ByteSource.Util.bytes(sysUser.getUsername());
                SimpleHash simpleHash = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
                sysUser.setPassword(simpleHash.toString());
                sysUserService.sysuserRegisr(sysUser);
                return 1;
            } else {
                return 0;
            }
            }return 0;
        }



        @RequestMapping("/sysuserlogin")
    public Integer sysuserlogin(@RequestBody SysUser sysUser){

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(),sysUser.getPassword());
            try {
                System.out.println(token);
                subject.login(token);
                if (subject.isAuthenticated()){
                    return 1;

                }else {
                    return 0;
                }

            }catch (Exception e){
                e.printStackTrace();

            }
            return 0;
        }

}
