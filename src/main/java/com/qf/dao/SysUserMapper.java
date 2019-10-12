package com.qf.dao;

import com.qf.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {


    public void updateSysUser(SysUser sysUser);
}
