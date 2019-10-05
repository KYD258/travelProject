package com.qf.responses;

import com.qf.domain.SysOrder;
import com.qf.domain.SysRoute;
import com.qf.domain.SysUser;
import com.qf.domain.TbUser;
import lombok.Data;

@Data
public class SysOrderEncs {
    private Integer orderId;
    private TbUser tbUser;
    private SysRoute sysRoute;
}
