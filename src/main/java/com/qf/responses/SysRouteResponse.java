package com.qf.responses;


import lombok.Data;

import java.util.List;

@Data
public class SysRouteResponse<T> {
    private List<T> list;
    private Long total;
}
