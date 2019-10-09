package com.qf.dao;

import com.qf.domain.SysRoute;
import com.qf.responses.SysOrderResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysOrderMapper {

    List<SysRoute> cartSelectAll(Integer userId);
}
