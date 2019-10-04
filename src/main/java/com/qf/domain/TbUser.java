package com.qf.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TbUser {
    private Integer userId;
    private String loginName;
    private String realName;
    private Integer sex;
    private Integer age;
    private String address;
    private String password;
    private String phone;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private String pic;
}
