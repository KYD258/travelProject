package com.qf.service.impl;

import com.qf.dao.UserCodeMapper;
import com.qf.domain.UserCode;
import com.qf.service.UserCodeService;
import com.qf.utils.EmailUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserCodeServiceImpl implements UserCodeService {
    @Resource
    private UserCodeMapper userCodeMapper;
    @Resource
    private EmailUtils emailUtils;
    @Override
    public boolean saveCode(String email) {
        String code = emailUtils.sendEmail(email);
        UserCode userCode = new UserCode();
        userCode.setEmail(email);
        userCode.setCodeNum(code);
        userCode.setStatus(1);
        if(userCodeMapper.findByEmail(email)!=null){
            return userCodeMapper.updateCode(userCode) > 0;
        }
        return userCodeMapper.saveCode(userCode) > 0;
    }

}
