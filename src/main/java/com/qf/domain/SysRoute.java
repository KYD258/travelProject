package com.qf.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@Table(name = "sys_route")
public class SysRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Integer routeId;
    @Column(name = "route_name")
    private String routeName;
    @Column(name = "route_area")
    private String routeArea;
    @Column(name = "route_info1")
    private String info1;
    @Column(name = "route_info2")
    private String info2;
    @Column(name = "route_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date routeTime;
    @Column(name = "route_price")
    private Float routePrice;
    @Column(name = "route_num")
    private Integer routeNum;
    @Column(name = "route_day")
    private Integer routeDay;
    @Column(name = "route_pic")
    private String routePic;

}
