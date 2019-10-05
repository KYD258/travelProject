package com.qf.responses;

import lombok.Data;

import java.util.List;

@Data
public class SysOrderResponse {
    private List list;
    private long total;
}
