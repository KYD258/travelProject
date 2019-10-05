package com.qf.dao;

import com.qf.domain.SysOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysOrderRepository  extends JpaRepository<SysOrder,Integer> {
}
