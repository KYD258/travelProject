package com.qf.dao;

import com.qf.domain.SysAttr;
import com.qf.domain.SysRouteAttr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysAttrRepository extends JpaRepository<SysAttr,Integer> {
    SysAttr findByAttrName(String attrName);
}
