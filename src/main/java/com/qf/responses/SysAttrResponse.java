package com.qf.responses;

import lombok.Data;

import java.util.List;

@Data
public class SysAttrResponse<T> {
    private List<T> list;
    private Long total;
}
