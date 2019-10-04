package com.qf.domain;

import lombok.Data;

@Data
public class UserCode {
    private Integer codeId;
    private String codeNum;
    private String email;
    private Integer status;
}
