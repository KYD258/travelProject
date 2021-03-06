package com.qf.service.impl;

import com.qf.dao.CartMapper;
import com.qf.dao.CartRepository;
import com.qf.domain.Cart;
import com.qf.domain.SysOrder;
import com.qf.domain.SysRoute;
import com.qf.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Resource
    private CartMapper cartMapper;
    @Override
    public List<SysRoute> cartSelectAll(Integer userId) {
        List<SysRoute> sysRoutes = cartMapper.cartSelectAll(userId);
        return sysRoutes;

    }

    @Override
    public void savecart(Cart cart) {
        cartRepository.saveAndFlush(cart);
    }

    @Override
    public Integer countcart(Integer userId) {
        return cartMapper.countcart(userId);
    }
}
