package com.qf.controller;

import com.qf.domain.SysOrder;
import com.qf.domain.SysRoute;
import com.qf.domain.TbUser;
import com.qf.responses.SysOrderEncs;
import com.qf.responses.SysOrderResponse;
import com.qf.service.SysOrderService;
import org.apache.catalina.session.StandardSession;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/sos")
public class SysOrderController {
    @Autowired
    private SysOrderService sysOrderService;
    @RequestMapping("/selectAll/{page}/{size}")
    public SysOrderResponse selectAll(@PathVariable Integer size, @PathVariable Integer page){
        SysOrderResponse sysOrderResponse = sysOrderService.selectAll(size,page);
        return sysOrderResponse;
    }
    @RequestMapping("/selectOrder")
    public SysOrderEncs selectOrder(@RequestBody SysOrder sysOrder){
        SysOrderEncs sysOrderEncs = sysOrderService.selectOrder(sysOrder.getOrderId());
        return sysOrderEncs;

    }
    @RequestMapping("/orderUpdate")
    public SysOrder updateOrder(@RequestBody SysOrder sysOrder){
        System.out.println(sysOrder);
        SysOrder sysOrder1 = sysOrderService.updateOrder(sysOrder);
        return sysOrder1;
    }
    @RequestMapping("/deleteOrder")
    public String deleteOrder(@RequestBody SysOrder sysOrder){
        Integer orderId = sysOrder.getOrderId();
        String s = sysOrderService.orderDelete(orderId);
        return s;
    }

    @RequestMapping("/savecart")

    public void savecart(HttpSession session,@RequestBody SysRoute sysRoute){
        System.out.println(sysRoute);

        Integer userId = (Integer)session.getAttribute("userId");
        System.out.println(userId+"++++++++++++++");
        SysOrder sysOrder=new SysOrder();
        sysOrder.setUserId(userId);
        sysOrder.setRouteId(sysRoute.getRouteId());
        sysOrderService.savecart(sysOrder);

    }

    //购物车
    @RequestMapping("/cartSelectAll")
    public List<SysRoute> cartSelectAll(HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");

        //System.out.println(userId+"+++++++++++");

       // System.out.println(sysRoute+"-----------");
        List<SysRoute> sysRoute = sysOrderService.cartSelectAll(userId);
        return sysRoute;
    }
}
