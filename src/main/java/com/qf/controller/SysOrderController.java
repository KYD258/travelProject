package com.qf.controller;

import com.qf.domain.SysOrder;
import com.qf.domain.SysRoute;
import com.qf.responses.SysOrderEncs;
import com.qf.responses.SysOrderResponse;
import com.qf.service.SysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
