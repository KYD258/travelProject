package com.qf.controller;

import com.qf.dao.SysOrderMapper;
import com.qf.dao.SysOrderRepository;
import com.qf.domain.Cart;
import com.qf.domain.SysOrder;
import com.qf.domain.SysRoute;
import com.qf.service.SysOrderService;
import com.qf.service.impl.SysOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class TbUserOrderController {
    @Autowired
    private SysOrderRepository sysOrderRepository;
    @Resource
    private SysOrderMapper sysOrderMapper;
    @Autowired
    private SysOrderServiceImpl sysOrderService;
    @RequestMapping("/orderDel")
    public String del(@RequestBody SysRoute sysRoute, HttpSession httpSession){
        Integer userId =(Integer) httpSession.getAttribute("userId");
        Integer routeId = sysRoute.getRouteId();
        SysOrder c=new SysOrder();
        c.setUserId(userId);
        c.setRouteId(routeId);
        sysOrderMapper.deleteByUserIdAndRouteId(c);

        return "删除成功";
    }
    @RequestMapping("/orderFindAll")
    public List<SysRoute> orderSelectAll(HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");
        List<SysRoute> sysRoutes =sysOrderService.findAll(userId);
        return sysRoutes;
    }
}
