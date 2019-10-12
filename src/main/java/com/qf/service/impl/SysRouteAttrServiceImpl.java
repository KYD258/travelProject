package com.qf.service.impl;

import com.qf.dao.SysRouteAttrRepository;
import com.qf.domain.SysRouteAttr;
import com.qf.service.SysRouteAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRouteAttrServiceImpl implements SysRouteAttrService {
    @Autowired
    private SysRouteAttrRepository sysRouteAttrRepository;

    @Override
    public SysRouteAttr save(SysRouteAttr sysRouteAttr) {
        SysRouteAttr save = sysRouteAttrRepository.save(sysRouteAttr);
        return save;
    }
}
