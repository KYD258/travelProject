package com.qf.controller;

import com.alibaba.fastjson.JSON;
import com.qf.domain.SysAttr;
import com.qf.domain.SysRoute;
import com.qf.domain.SysRouteAttr;
import com.qf.responses.SysAttrResponse;
import com.qf.service.SysAttrService;
import com.qf.service.SysRouteAttrService;
import com.qf.utils.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("/attr")
public class SysAttrController {
    @Autowired
    private SysAttrService sysAttrService;
    @Autowired
    private UploadUtils uploadUtils;
    @Autowired
    private SysRouteAttrService sysRouteAttrService;

    private Logger logger = LoggerFactory.getLogger(SysAttrController.class);
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
        System.out.println(sysAttr);
        sysAttrService.save(sysAttr);
        if(sysAttr!=null){
            Integer attrId = sysAttrService.findByName(sysAttr.getAttrName()).getAttrId();
            Integer routeId = sysAttr.getRouteId();
            SysRouteAttr sysRouteAttr =new SysRouteAttr();
            sysRouteAttr.setAttrId(attrId);
            sysRouteAttr.setRouteId(routeId);
            sysRouteAttrService.save(sysRouteAttr);
        }
        return "添加成功";
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

    @RequestMapping("/delete")
    public String delete(@RequestBody SysAttr sysAttr){
        return sysAttrService.delete(sysAttr.getAttrId());
    }

    @RequestMapping("/update")
    public String update(@RequestBody SysAttr sysAttr){
        return sysAttrService.update(sysAttr);
    }
}
