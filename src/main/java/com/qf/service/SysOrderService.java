package com.qf.service;

import com.qf.domain.SysOrder;
import com.qf.domain.SysRoute;
import com.qf.responses.SysOrderEncs;
import com.qf.responses.SysOrderResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface SysOrderService {
   SysOrderResponse selectAll(Integer size,Integer page);

   SysOrderEncs selectOrder(Integer orderId);

   SysOrder updateOrder(SysOrder sysOrder);

   String orderDelete(Integer orderId);

    List<SysRoute> cartSelectAll(Integer userId);


    void savecart(SysOrder sysOrder);
}
