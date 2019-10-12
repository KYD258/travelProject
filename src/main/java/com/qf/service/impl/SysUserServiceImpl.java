package com.qf.service.impl;

import com.qf.dao.SysUserMapper;
import com.qf.dao.SysUserRepository;
import com.qf.domain.SysUser;
import com.qf.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRepository sysUserRepository;

//    @Override
//    public String findByUsername1(String username) {
//        String password="";
//        if (sysUserRepository.findByUsername(username)!=null){
//           password = sysUserRepository.findByUsername(username).getPassword();
//            return password;
//        }
//
//        return "fial";
//    }

    @Override
    public SysUser findByUsername(String username) {
            if (sysUserRepository.findByUsername(username)!=null){
                SysUser sysUser = sysUserRepository.findByUsername(username);
                return sysUser;
        }
        return null;
    }

    @Override
    public void sysuserRegisr(SysUser sysUser) {

        sysUserRepository.save(sysUser);

    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public SysUser findByUserId(Integer userid) {
        return sysUserRepository.findByUserid(userid);
    }
}
