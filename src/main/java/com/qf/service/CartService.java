package com.qf.service;

import com.qf.domain.Cart;
import com.qf.domain.SysOrder;
import com.qf.domain.SysRoute;

import java.util.List;

public interface CartService {

    List<SysRoute> cartSelectAll(Integer userId);


    void savecart(Cart cart);
}
