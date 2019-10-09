package com.qf.service.impl;

import com.qf.dao.SysRouteMapper;
import com.qf.dao.SysRouteRepository;
import com.qf.domain.SysRoute;
import com.qf.responses.SysRouteResponse;
import com.qf.service.SysRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SysRouteServiceImpl implements SysRouteService {
    @Autowired
    private SysRouteRepository sysRouteRepository;

    @Autowired
    private SysRouteMapper sysRouteMapper;

    @Override
    public String save(SysRoute sysRoute) {
        sysRouteRepository.save(sysRoute);
        return "新增成功";
    }

    @Override
    public String delete(Integer routeId) {
        sysRouteRepository.deleteById(routeId);
        return "删除成功";
    }

    @Override
    public String update(SysRoute sysRoute) {
        sysRouteRepository.saveAndFlush(sysRoute);
        return "修改成功";
    }

    @Override
    public SysRouteResponse selectAll(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SysRoute> all = sysRouteRepository.findAll(pageable);
        SysRouteResponse res=new SysRouteResponse();
        res.setList(all.getContent());
        res.setTotal(all.getTotalElements());
        return res;
    }

    @Override
    public SysRoute selectById(Integer routeId) {
        Optional<SysRoute> byId = sysRouteRepository.findById(routeId);
        if(byId.isPresent()){
            SysRoute sysRoute=byId.get();
            return sysRoute;
        }
        return null;
    }

    @Override
    public SysRoute selectByattrId(Integer attrId) {
        SysRoute sysRoute = sysRouteMapper.findByattrId(attrId);
        if(sysRoute!=null){
            return sysRoute;
        }
        return null;
    }
}
