package com.qf.controller;

import com.alibaba.fastjson.JSON;
import com.qf.domain.SysAttr;
import com.qf.domain.SysRoute;
import com.qf.responses.SysRouteResponse;
import com.qf.service.SysRouteService;
import com.qf.utils.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("/route")
public class SysRouteController {
    @Autowired
    private SysRouteService sysRouteService;
    @Autowired
    private UploadUtils uploadUtils;
    private Logger logger = LoggerFactory.getLogger(SysAttrController.class);

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

    @RequestMapping("/getPath")
    public String getPath(MultipartFile file){
        logger.debug("传入的文件参数:{}",JSON.toJSONString(file,true));
        if(Objects.isNull(file) || file.isEmpty()){
            logger.error("文件为空");
        }else {
            String path = uploadUtils.getPath(file);
            return path;
        }
        return null;
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
