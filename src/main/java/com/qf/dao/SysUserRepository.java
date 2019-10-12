package com.qf.dao;

import com.qf.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser,Integer> {
    SysUser findByUsername(String username);

    SysUser findByUserid(Integer userid);
}
