package com.qf.service.impl;

import com.qf.dao.SysAttrRepository;
import com.qf.domain.SysAttr;
import com.qf.responses.SysAttrResponse;
import com.qf.service.SysAttrService;
import com.qf.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class SysAttrServiceImpl implements SysAttrService {
    @Autowired
    private SysAttrRepository sysAttrRepository;



    @Override
    public String save(SysAttr sysAttr) {
        sysAttrRepository.save(sysAttr);
        return "新增成功";
    }

    @Override
    public String delete(Integer attrId) {
        sysAttrRepository.deleteById(attrId);
        return "删除成功";
    }

    @Override
    public String update(SysAttr sysAttr) {
        sysAttrRepository.saveAndFlush(sysAttr);
        return "修改成功";
    }

    @Override
    public SysAttrResponse selectAll(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SysAttr> all = sysAttrRepository.findAll(pageable);
        SysAttrResponse res=new SysAttrResponse();
        res.setList(all.getContent());
        res.setTotal(all.getTotalElements());
        return res;
    }

    @Override
    public SysAttr selectById(Integer attrId) {
        Optional<SysAttr> byId = sysAttrRepository.findById(attrId);
        if(byId.isPresent()){
            SysAttr sysAttr = byId.get();
            return sysAttr;
        }
        return null;
    }

    @Override
    public SysAttr findByName(String attrName) {
        return sysAttrRepository.findByAttrName(attrName);
    }

}
