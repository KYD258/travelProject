package com.qf.responses;

import com.qf.domain.TbUser;
import lombok.Data;

@Data
public class TbUserResponse {

    private TbUser tbUser;
    private String code;
}
