package com.qf.controller;

import com.qf.commons.R;
import com.qf.domain.TbUser;
import com.qf.responses.TbUserResponse;
import com.qf.service.TbUserService;
import com.qf.service.UserCodeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class TbUserController {

    @Resource
    private UserCodeService userCodeService;
    @Resource
    private TbUserService tbUserService;

    //用户登录
    @RequestMapping("/userLogin")
    public R userLogin(@RequestBody TbUser tbUser, HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(tbUser.getLoginName(), tbUser.getPassword());
        try {
            subject.login(token);
            if(subject.isAuthenticated()){

                TbUser tbUser1 = tbUserService.selectByUserId(tbUser.getLoginName());
                System.out.println(tbUser1+"++++++++++++++++++++++");
                Integer userId = tbUser1.getUserId();
                session.setAttribute("userId",userId);
                System.out.println(userId+"-+-+-+-+-+");
                return R.ok();
            }
        }catch (Exception e){
            return R.error();
        }
        return R.error();
    }

    //获取验证码
    @RequestMapping("/getCode/{email}")
    public R getCode(@PathVariable String email){

        if(userCodeService.saveCode(email)){
            return R.ok();
        }
        return R.error();
    }

    //用户注册
    @RequestMapping("/userRegister")
    public R register(@RequestBody TbUserResponse tbUserResponse){
        if(tbUserService.register(tbUserResponse.getTbUser(),tbUserResponse.getCode())){
            return R.ok();
        }
        return R.error();
    }
}
