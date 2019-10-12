package com.qf.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_route_attr")
public class SysRouteAttr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "route_id")
    private Integer routeId;
    @Column(name = "attr_id")
    private Integer attrId;
}
