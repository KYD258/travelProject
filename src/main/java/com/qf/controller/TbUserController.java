package com.qf.controller;

import com.alibaba.fastjson.JSON;
import com.qf.commons.R;
import com.qf.domain.TbUser;
import com.qf.responses.TbUserResponse;
import com.qf.service.TbUserService;
import com.qf.service.UserCodeService;
import com.qf.utils.UploadUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class TbUserController {

    @Resource
    private UserCodeService userCodeService;
    @Resource
    private TbUserService tbUserService;
    @Autowired
    private UploadUtils uploadUtils;
    private Logger logger = LoggerFactory.getLogger(SysAttrController.class);

    //用户登录
    @RequestMapping("/userLogin")
    public R userLogin(@RequestBody TbUser tbUser, HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(tbUser.getLoginName(), tbUser.getPassword());
        try {
            subject.login(token);
            if(subject.isAuthenticated()){

                TbUser tbUser1 = tbUserService.selectByUserId(tbUser.getLoginName());
                Integer userId = tbUser1.getUserId();
                session.setAttribute("userId",userId);
                return R.ok();
            }
        }catch (Exception e){
            return R.error();
        }
        return R.error();
    }

    @RequestMapping("outLogin")
    public void outLogin(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    //获取验证码
    @RequestMapping("/getCode/{email}")
    public R getCode(@PathVariable String email){

        if(userCodeService.saveCode(email)){
            return R.ok();
        }
        return R.error();
    }

    @RequestMapping("/getPath")
    public String getPath(MultipartFile file){
        logger.debug("传入的文件参数:{}",JSON.toJSONString(file,true));
        if(Objects.isNull(file) || file.isEmpty()){
            logger.error("文件为空");
        }else {
            String path = uploadUtils.getPath(file);
            return path;
        }
        return null;
    }
    //用户注册
    @RequestMapping("/userRegister")
    public R register(@RequestBody TbUserResponse tbUserResponse){
        if(tbUserService.register(tbUserResponse.getTbUser(),tbUserResponse.getCode())){
            return R.ok();
        }
        return R.error();
    }
    @RequestMapping("/getMsg")
    public TbUser getMsg(HttpSession httpSession){
        Integer userId =(Integer) httpSession.getAttribute("userId");
        TbUser one = tbUserService.findOne(userId);
        return one;

    }

    @RequestMapping("/tbUserUpdate")
    public R tbUserUpdate(@RequestBody TbUser tbUser){
        if(tbUserService.updateUser(tbUser)){
            return R.ok();
        }
        return R.error();
    }

}
