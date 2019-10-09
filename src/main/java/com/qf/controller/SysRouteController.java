package com.qf.controller;

import com.qf.domain.SysAttr;
import com.qf.domain.SysRoute;
import com.qf.responses.SysRouteResponse;
import com.qf.service.SysRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/route")
public class SysRouteController {
    @Autowired
    private SysRouteService sysRouteService;

    @RequestMapping("/selectAll/{page}/{size}")
    public SysRouteResponse selectAll(@PathVariable Integer size, @PathVariable Integer page){
        SysRouteResponse sysRouteResponse = sysRouteService.selectAll(size, page);
        return  sysRouteResponse;
    }

    @RequestMapping("/selectById")
    public SysRoute selectById(@RequestBody SysRoute sysRoute){
        return sysRouteService.selectById(sysRoute.getRouteId());
    }

    @RequestMapping("/delete")
    public String delete(@RequestBody SysRoute sysRoute){
        return sysRouteService.delete(sysRoute.getRouteId());
    }

    @RequestMapping("/save")
    public String save(@RequestBody SysRoute sysRoute){
        return sysRouteService.save(sysRoute);
    }

    @RequestMapping("/update")
    public String update(@RequestBody SysRoute sysRoute){
        return sysRouteService.update(sysRoute);
    }

    @RequestMapping("/selectByattrId")
    public SysRoute selectByattrId(@RequestBody SysAttr sysAttr){
        return sysRouteService.selectByattrId(sysAttr.getAttrId());
    }
}
