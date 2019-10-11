package com.qf.service;

import com.qf.dao.SysRouteRepository;
import com.qf.domain.SysAttr;
import com.qf.domain.SysRoute;
import com.qf.responses.SysRouteResponse;

import java.util.List;

public interface SysRouteService {
    String save(SysRoute sysRoute);

    String delete(Integer routeId);

    String update(SysRoute sysRoute);

    SysRouteResponse selectAll(Integer size, Integer page);

    SysRoute selectById(Integer routeId);

    SysRoute selectByattrId(Integer attrId);

    List<SysAttr> selectByrouteId(Integer routeId);

    List<SysRoute> findByRouteNameLike(String routeName);
}
