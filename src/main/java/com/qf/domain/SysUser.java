package com.qf.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sys_user")
public class SysUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_id")
    private Integer userid;
    @Column(name = "user_name")
    private String username;
    private String password;
    private String phone;
    private String email;


}
