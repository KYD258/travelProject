package com.qf.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "tb_user")
public class TbUser {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "login_name")
    private String loginName;
    @Column(name = "real_name")
    private String realName;
    private String sex;
    private Integer age;
    private String address;
    private String password;
    private String phone;
    private String email;
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private String pic;
}
