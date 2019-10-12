package com.qf.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_attr")
public class SysAttr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attr_id")
    private Integer attrId;
    @Column(name = "attr_name")
    private String attrName;
    @Column(name = "attr_pic")
    private String attrPic;
    @Column(name = "attr_address")
    private String attrAddress;
    @Column(name = "attr_info")
    private String attrInfo;
    @Column(name = "attr_price")
    private Float attrPrice;
    @Column(name = "route_id")
    private Integer routeId;
}
