package cn.com.teacher.controller;
import cn.com.teacher.bean.History;
import cn.com.teacher.bean.Resources;
import cn.com.teacher.bean.UserCollection;
import cn.com.teacher.service.ResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String h_time = df.format(new Date());
        history.setH_content(split[0]);
        history.setH_path(split[1]);
        history.setH_time(h_time);
        int i = resourcesService.addResourcesHistory(history);
        if(i==1){
            System.out.println("插入历史记录成功");
        }
    }

    @ResponseBody
    @GetMapping(value = "/searchResourcesHistory")
    public List<History> getAllHistory(){
        List<History> allHistory = resourcesService.getAllHistory();
        System.out.println("getAllHistory ="+allHistory);
        return allHistory;
    }


    @ResponseBody
    @GetMapping(value = "/searchHistory")
    public List<History> getSearchHistory(@RequestParam String h_content){
        List<History> searchHistory = resourcesService.getSearchHistory(h_content);
        System.out.println("getSearchHistory ="+searchHistory);
        return searchHistory;
    }


    @ResponseBody
    @GetMapping(value = "/addCollection")
    public String addResourcesCollection(@RequestParam String content){
        UserCollection userCollection=new UserCollection();
        String[] split = content.split(":");
        userCollection.setC_content(split[0]);
        userCollection.setC_path(split[1]);
        try{
            if(resourcesService.checkLove(split[1])==null){
                int i = resourcesService.addResourcesCollection(userCollection);
                if(i==1){
                    return "收藏成功";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "收藏重复了.......";
    }


    @ResponseBody
    @GetMapping(value = "/getLoveMovie")
    public List<UserCollection> getLoveResources(){
        List<UserCollection> loveResources = resourcesService.getLoveResources();
        System.out.println("getLoveResources ="+loveResources);
        return loveResources;
    }

    @ResponseBody
    @GetMapping(value = "/searchLoveMovie")
    public List<UserCollection> getSearchLoveMovie(@RequestParam String c_content){
        List<UserCollection> searchLoveMovie = resourcesService.getSearchLoveMovie(c_content);
        System.out.println("getSearchLoveMovie ="+searchLoveMovie);
        return searchLoveMovie;
    }


    @ResponseBody
    @GetMapping(value = "/deleteLoveMovie")
    public String deleteLoveMovie(@RequestParam String content) {
        UserCollection userCollection = new UserCollection();
        userCollection.setC_path(content);
        int i = resourcesService.deleteLoveMovie(userCollection);
        if (i == 1) {
            return "取消收藏成功";
        }
        return "取消收藏失败了.......";
    }

}
