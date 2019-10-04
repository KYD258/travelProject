package com.qf.dao;

import com.qf.domain.UserCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCodeMapper {
    int saveCode(UserCode userCode);

    UserCode findByEmail(String email);

    int updateCode(UserCode userCode);

    int updateStatus(String email);
}
