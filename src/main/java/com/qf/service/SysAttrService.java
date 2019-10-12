package com.qf.service;

import com.qf.domain.SysAttr;
import com.qf.responses.SysAttrResponse;
import org.springframework.web.multipart.MultipartFile;

public interface SysAttrService {
    String save(SysAttr sysAttr);

    String delete(Integer attrId);

    String update(SysAttr sysAttr);

    SysAttrResponse selectAll(Integer size, Integer page);

    SysAttr selectById(Integer attrId);

    SysAttr findByName(String attrName);
}
