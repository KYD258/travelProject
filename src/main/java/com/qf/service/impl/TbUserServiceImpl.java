package com.qf.service.impl;

import com.qf.dao.TbUserMapper;
import com.qf.dao.UserCodeMapper;
import com.qf.domain.SysRoute;
import com.qf.domain.TbUser;
import com.qf.domain.UserCode;
import com.qf.service.TbUserService;
import com.qf.utils.Md5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    private TbUserMapper tbUserMapper;

    @Resource
    private UserCodeMapper userCodeMapper;

    @Override
    public boolean register(TbUser tbUser,String code) {
        UserCode userCode = userCodeMapper.findByEmail(tbUser.getEmail());
        if(tbUserMapper.findByLoginName(tbUser.getLoginName())==null
                && code.equals(userCode.getCodeNum()) && userCode.getStatus()==1){
            userCodeMapper.updateStatus(tbUser.getEmail());
            String pass = Md5Utils.getMd5Password(tbUser.getPassword());
            tbUser.setPassword(pass);
            return tbUserMapper.insertUser(tbUser) > 0;
        }
        return false;
    }

    @Override
    public TbUser selectByUserId(String loginName) {
        return tbUserMapper.selectByUserId(loginName);
    }
}
