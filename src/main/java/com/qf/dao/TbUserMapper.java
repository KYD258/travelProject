package com.qf.dao;

import com.qf.domain.SysRoute;
import com.qf.domain.TbUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserMapper {

    TbUser findByLoginName(String loginName);

    int insertUser(TbUser tbUser);


    TbUser selectByUserId(String loginName);
}
