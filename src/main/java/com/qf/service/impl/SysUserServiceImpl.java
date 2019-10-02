package com.qf.service.impl;

import com.qf.dao.SysUserRepository;
import com.qf.domain.SysUser;
import com.qf.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

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
    public String findByUsername(String username) {
        String sysuser="";
            if (sysUserRepository.findByUsername(username)!=null){
                SysUser sysUser = sysUserRepository.findByUsername(username);
                return sysuser;
    }

        return "fial";
    }

    @Override
    public void sysuserRegisr(SysUser sysUser) {

        sysUserRepository.save(sysUser);

    }
}
