package com.qf.service.impl;

import com.qf.dao.*;
import com.qf.domain.*;
import com.qf.responses.SysOrderEncs;
import com.qf.responses.SysOrderResponse;
import com.qf.service.SysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class SysOrderServiceImpl implements SysOrderService {
    @Autowired
    private TbUserRepository tbUserRepository;
    @Autowired
    private SysRouteRepository sysRouteRepository;
    @Autowired
    private SysOrderRepository sysOrderRepository;
    @Resource
    private SysOrderMapper sysOrderMapper;

    @Resource
    private CartMapper cartMapper;



    @Override
    public String orderDelete(Integer orderId) {
        sysOrderRepository.deleteById(orderId);
        return "删除成功";
    }

    @Override
    public List<SysRoute> findAll(Integer userId) {
        List<SysOrder> all = sysOrderMapper.findAll(userId);
        List<SysRoute> list=new ArrayList<>();
        for (SysOrder sysOrder: all) {
            Optional<SysRoute> byId = sysRouteRepository.findById(sysOrder.getRouteId());
            if (byId!=null){
                SysRoute sysRoute = byId.get();
                list.add(sysRoute);
            }
        }
        return list;
    }


    @Override
    public SysOrder updateOrder(SysOrder sysOrder) {
        SysOrder sysOrder1 = sysOrderRepository.saveAndFlush(sysOrder);
        return sysOrder1;
    }

    @Override
    public SysOrderEncs selectOrder(Integer orderId) {
        Integer routeId=0;
        Integer userId=0;
        SysRoute sysRoute=null;
        TbUser tbUser=null;

        Optional<SysOrder> byId = sysOrderRepository.findById(orderId);
        if (byId!=null){
            SysOrder sysOrder = byId.get();
             routeId = sysOrder.getRouteId();
             userId = sysOrder.getUserId();
        }
        Optional<SysRoute> byId1 = sysRouteRepository.findById(routeId);
        if (byId1!=null){
            sysRoute = byId1.get();
        }
        Optional<TbUser> byId2 = tbUserRepository.findById(userId);
        if (byId2!=null){
            tbUser = byId2.get();
        }
        SysOrderEncs sysOrderEncs=new SysOrderEncs();
        sysOrderEncs.setTbUser(tbUser);
        sysOrderEncs.setSysRoute(sysRoute);
        sysOrderEncs.setOrderId(orderId);

        return sysOrderEncs;
    }

    @Override
    public SysOrderResponse selectAll(Integer size,Integer page) {
        List<SysOrderEncs> list=new ArrayList<SysOrderEncs>();
        Pageable pageable=PageRequest.of(page-1,size);
        Page<SysOrder> content = sysOrderRepository.findAll(pageable);
        long total = content.getTotalElements();
        List<SysOrder> all = content.getContent();
        for (SysOrder a:all) {
            SysOrderEncs sysOrderEncs=new SysOrderEncs();
            sysOrderEncs.setOrderId(a.getOrderId());
            Optional<TbUser> byId = tbUserRepository.findById(a.getUserId());
            if (byId!=null){
                TbUser tbUser = byId.get();
                sysOrderEncs.setTbUser(tbUser);
            }
            Optional<SysRoute> byId1 = sysRouteRepository.findById(a.getRouteId());
            if (byId1!=null){
                SysRoute sysRoute = byId1.get();
                sysOrderEncs.setSysRoute(sysRoute);
            }
            list.add(sysOrderEncs);
        }
        SysOrderResponse sysOrderResponse=new SysOrderResponse();
        sysOrderResponse.setList(list);
        sysOrderResponse.setTotal(total);
        return sysOrderResponse;
    }
}
