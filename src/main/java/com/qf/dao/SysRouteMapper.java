package com.qf.dao;

import com.qf.domain.SysRoute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRouteMapper {
    SysRoute findByattrId(Integer attrId);
}
