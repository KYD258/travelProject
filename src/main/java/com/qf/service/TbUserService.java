package com.qf.service;

import com.qf.domain.TbUser;

public interface TbUserService {


    boolean register(TbUser tbUser,String code);
}
