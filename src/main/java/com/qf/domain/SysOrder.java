package com.qf.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sys_order")
public class SysOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "route_id")
    private Integer routeId;

}
