package cn.com.teacher.controller;

import cn.com.teacher.bean.History;
import cn.com.teacher.bean.Resources;
import cn.com.teacher.service.ResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    @ResponseBody
    @GetMapping(value = "/addHistory")
    public void addResourcesHistory(@RequestParam String content){
        History history=new History();
        String[] split = content.split(":");
        String h_content=split[0];
        String h_path=split[1];
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String h_time = df.format(new Date());
        history.setH_content(h_content);
        history.setH_path(h_path);
        history.setH_time(h_time);
        int i = resourcesService.addResourcesHistory(history);
        if(i==1){
            System.out.println("成功");
        }
    }
}
