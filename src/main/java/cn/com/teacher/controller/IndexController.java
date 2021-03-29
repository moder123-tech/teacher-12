package cn.com.teacher.controller;

import cn.com.teacher.bean.Resources;
import cn.com.teacher.service.ResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wx
 * @version 1.0
 * @date 2021/3/19 20:15
 */
@Controller
@Slf4j
public class IndexController {

    @Autowired
    public ResourcesService resourcesService;

    @ResponseBody
    @GetMapping(value = "/getMovie")
    public List<Resources> getAllResources(){
        List<Resources> allResources = resourcesService.getAllResources();
        System.out.println("getAllResources ="+allResources);
        return allResources;
    }

    @ResponseBody
    @GetMapping(value = "/searchMovie")
    public List<Resources> getSearchResources(@RequestParam String r_content){
        List<Resources> searchResources = resourcesService.getSearchResources(r_content);
        System.out.println("getSearchResources ="+searchResources);
        return searchResources;
    }
}
