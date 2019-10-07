package com.qf.service;

import com.qf.domain.SysAttr;
import com.qf.responses.SysAttrResponse;

public interface SysAttrService {
    String save(SysAttr sysAttr);

    String delete(Integer attrId);

    String update(SysAttr sysAttr);

    SysAttrResponse selectAll(Integer size, Integer page);

    SysAttr selectById(Integer attrId);
}
