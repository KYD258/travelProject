package com.qf.dao;

import com.qf.domain.SysRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysRouteRepository extends JpaRepository<SysRoute,Integer> {
    List<SysRoute> findByRouteNameLike(String routeName);
}
