package com.qf.dao;

import com.qf.domain.SysOrder;
import com.qf.domain.SysRoute;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysOrderMapper {
   void updateStatus(String orderNum);
   SysOrder findByRouteId(SysOrder sysOrder);
   List<SysOrder> findAll(Integer userId);
   void  deleteByUserIdAndRouteId(SysOrder sysOrder);
}
