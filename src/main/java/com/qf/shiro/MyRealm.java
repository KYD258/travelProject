package com.qf.shiro;

import com.qf.dao.SysUserRepository;
import com.qf.domain.SysUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private SysUserRepository sysUserRepository;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //管理员验证登陆
        String username = (String) authenticationToken.getPrincipal();
        //使用username查询password
        SysUser byUsername = sysUserRepository.findByUsername(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, byUsername.getPassword(),ByteSource.Util.bytes(username), getName());
        // System.out.println(simpleAuthenticationInfo+"111111111111111");
        return simpleAuthenticationInfo;

    }
}
