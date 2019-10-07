package com.qf.controller;

import com.qf.domain.SysAttr;
import com.qf.responses.SysAttrResponse;
import com.qf.service.SysAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attr")
public class SysAttrController {
    @Autowired
    private SysAttrService sysAttrService;

    @RequestMapping("/selectAll/{page}/{size}")
    public SysAttrResponse selectAll(@PathVariable Integer size, @PathVariable Integer page){
        SysAttrResponse sysAttrResponse = sysAttrService.selectAll(size, page);
        return sysAttrResponse;
    }

    @RequestMapping("/selectById")
    public SysAttr selectById(@RequestBody SysAttr sysAttr){
        return sysAttrService.selectById(sysAttr.getAttrId());
    }

    @RequestMapping("/save")
    public String save(@RequestBody SysAttr sysAttr){
        return sysAttrService.save(sysAttr);
    }

    @RequestMapping("/delete")
    public String delete(@RequestBody SysAttr sysAttr){
        return sysAttrService.delete(sysAttr.getAttrId());
    }

    @RequestMapping("/update")
    public String update(@RequestBody SysAttr sysAttr){
        return sysAttrService.update(sysAttr);
    }
}
