package com.qf.controller;

import com.alipay.api.AlipayApiException;
import com.qf.dao.CartMapper;
import com.qf.dao.CartRepository;
import com.qf.dao.SysOrderRepository;
import com.qf.domain.Cart;
import com.qf.domain.SysOrder;
import com.qf.domain.SysRoute;
import com.qf.utils.AlipayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/alipay")
public class AlipayController {
    
    @Autowired
    private AlipayUtils alipayUtils;
    @Resource
    private CartMapper cartMapper;
    @Autowired
    private SysOrderRepository sysOrderRepository;
    @Autowired
    private CartRepository cartRepository;

    @RequestMapping("/pay")
    public String pay(@RequestBody SysRoute sysRoute, HttpSession httpSession){
        Integer userId =(Integer) httpSession.getAttribute("userId");
        Integer routeId = sysRoute.getRouteId();
        SysOrder sysOrder=new SysOrder();
        sysOrder.setRouteId(routeId);
        sysOrder.setUserId(userId);
        SysOrder save = sysOrderRepository.save(sysOrder);
        String pay="";
        try {
            pay = alipayUtils.pay(sysRoute);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(pay);

        return pay;
    }

    @RequestMapping("/payAll")
    public String payAll(HttpSession httpSession){
        SysRoute sysRoute=new SysRoute();
        StringBuffer stringBuffer=new StringBuffer();
        Integer userId =(Integer) httpSession.getAttribute("userId");
        List<SysRoute> sysRoutes = cartMapper.cartSelectAll(userId);
        Float aFloat = cartMapper.countPrice(userId);
        String s1="";
        for (SysRoute s:sysRoutes) {
            StringBuffer append = stringBuffer.append(s.getRouteName());
             s1 = append.toString();
            SysOrder sysOrder=new SysOrder();
            sysOrder.setRouteId(s.getRouteId());
            sysOrder.setUserId(userId);
            SysOrder save = sysOrderRepository.save(sysOrder);
        }
        cartMapper.deleteAll(userId);
        sysRoute.setRouteName(s1);
        sysRoute.setRoutePrice(aFloat);
        String pay="";
        try {
            pay = alipayUtils.pay(sysRoute);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(pay);
        return pay;
    }

    @RequestMapping("/del")
    public String del(@RequestBody SysRoute sysRoute,HttpSession httpSession){
        Integer userId =(Integer) httpSession.getAttribute("userId");
        Integer routeId = sysRoute.getRouteId();
        Cart c=new Cart();
        c.setUserId(userId);
        c.setRouteId(routeId);
        System.out.println(c+"--------------------------------------->>>>");
        Cart byUserIdAndRouteId = cartMapper.findByUserIdAndRouteId(c);
        cartRepository.delete(byUserIdAndRouteId);

        return "删除成功";
    }
}
