package com.qf.service;

import com.qf.domain.SysUser;

public interface SysUserService {


    void sysuserRegisr(SysUser sysUser);

    SysUser findByUsername(String username);

    SysUser findByUserId(Integer userid);

    void updateSysUser(SysUser sysUser);
}
