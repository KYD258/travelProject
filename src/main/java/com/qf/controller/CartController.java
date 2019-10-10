package com.qf.controller;


import com.qf.domain.Cart;
import com.qf.domain.SysOrder;
import com.qf.domain.SysRoute;
import com.qf.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    //购物车
    @RequestMapping("/cartSelectAll")
    public List<SysRoute> cartSelectAll(HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");

        //System.out.println(userId+"+++++++++++");

        // System.out.println(sysRoute+"-----------");
        List<SysRoute> sysRoutes = cartService.cartSelectAll(userId);
        return sysRoutes;
    }

    @RequestMapping("/savecart")

    public void savecart(HttpSession session,@RequestBody SysRoute sysRoute){
        System.out.println(sysRoute);

        Integer userId = (Integer)session.getAttribute("userId");
        System.out.println(userId+"++++++++++++++");
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setRouteId(sysRoute.getRouteId());
        cartService.savecart(cart);

    }
}
