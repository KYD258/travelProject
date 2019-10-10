package com.qf.dao;

import com.qf.domain.Cart;
import com.qf.domain.SysRoute;
import com.qf.responses.SysOrderResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {

    List<SysRoute> cartSelectAll(Integer userId);


    Float countPrice(Integer userId);

    Cart findByUserIdAndRouteId(Cart cart);

    void deleteAll(Integer userId);
}
