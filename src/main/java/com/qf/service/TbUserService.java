package com.qf.service;

import com.qf.domain.SysRoute;
import com.qf.domain.TbUser;

public interface TbUserService {


    boolean register(TbUser tbUser,String code);

    TbUser selectByUserId(String loginName);

    TbUser findOne(Integer userId);

    boolean updateUser(TbUser tbUser);
}
