package com.qf.dao;

import com.qf.domain.SysAttr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysAttrMapper {
    List<SysAttr> findByrouteId(Integer routeId);
}
