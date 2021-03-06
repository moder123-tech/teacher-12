package cn.com.teacher.controller;

import cn.com.teacher.bean.Resources;
import cn.com.teacher.service.TypeResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : wx  //作者
 * @version 1.0 //版本
 * @date: 2021-03-27 14:13 //日期
 */
@Controller
@Slf4j
public class TypeController {

    @Autowired
    private TypeResourcesService typeResourcesService;


    /**
     * 传入标签内容@param r_label
     * 根据标签内容返回符合条件的视频资源@return
     */
    @ResponseBody
    @GetMapping(value = "/getTypeMovie")
    public List<Resources> getAllResources(@RequestParam String r_label) {
        List<Resources> typeResources = typeResourcesService.getTypeResources(r_label);
        System.out.println("typeResources =" + typeResources);
        return typeResources;
    }


}
