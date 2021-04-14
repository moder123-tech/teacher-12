package cn.com.teacher.controller;

import cn.com.teacher.bean.History;
import cn.com.teacher.bean.Resources;
import cn.com.teacher.bean.UserCollection;
import cn.com.teacher.service.ResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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

    /**
     * index.html页面一加载就触发这个方法,获得所有的视频资源
     * 返回所有视频资源集合@return
     */
    @ResponseBody
    @GetMapping(value = "/getMovie")
    public List<Resources> getAllResources() {
        List<Resources> allResources = resourcesService.getAllResources();
        System.out.println("getAllResources =" + allResources);
        return allResources;
    }

    /**
     * 传入要查询的内容@param r_content
     * 返回符合查询条件的视频资源集合@return
     */
    @ResponseBody
    @GetMapping(value = "/searchMovie")
    public List<Resources> getSearchResources(@RequestParam String r_content) {
        List<Resources> searchResources = resourcesService.getSearchResources(r_content);
        System.out.println("getSearchResources =" + searchResources);
        return searchResources;
    }

    /**
     * 从前端页面传入视频资源的路径跟标题内容@param content
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ResponseBody
    @GetMapping(value = "/addHistory")
    public void addResourcesHistory(@RequestParam String content, HttpSession session) {
        Integer uId = (Integer) session.getAttribute("uId");
        History history = new History();
        String[] split = content.split(":");
        Resources resources = resourcesService.getLabel(split[1]);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String h_time = df.format(new Date());
        history.setH_content(split[0]);
        history.setH_path(split[1]);
        history.setH_time(h_time);
        history.setH_forrign(uId + "");
        history.setH_label(resources.getR_label());
        int i = resourcesService.addResourcesHistory(history);
        if (i == 1) {
            System.out.println("插入历史记录成功");
        }
    }

    /**
     * 查询历史表单,得到用户的历史记录@return
     */
    @ResponseBody
    @GetMapping(value = "/searchResourcesHistory")
    public List<History> getAllHistory(HttpSession session) {
        Integer uId = (Integer) session.getAttribute("uId");
        List<History> allHistory = resourcesService.getAllHistory(uId + "");
        System.out.println("getAllHistory =" + allHistory);
        return allHistory;
    }


    /**
     * 传入所有查询的内容@param h_content
     * 返回符合条件的历史记录集合@return
     */
    @ResponseBody
    @GetMapping(value = "/searchHistory")
    public List<History> getSearchHistory(@RequestParam String h_content, HttpSession session) {
        Integer uId = (Integer) session.getAttribute("uId");
        List<History> searchHistory = resourcesService.getSearchHistory(h_content, uId + "");
        System.out.println("getSearchHistory =" + searchHistory);
        return searchHistory;
    }

    /**
     * 传入要删除历史记录所对应的时间@param h_time
     * 返回剩下的历史记录集合@return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ResponseBody
    @GetMapping(value = "/deleteHistory")
    public List<History> getDeleteHistory(@RequestParam String h_time, HttpSession session) {
        Integer uId = (Integer) session.getAttribute("uId");
        resourcesService.deleteHistory(h_time, uId + "");
        List<History> allHistory = this.getAllHistory(session);
        System.out.println("删除历史记录成功");
        return allHistory;
    }


    /**
     * 从前端页面传入视频资源的路径跟标题内容@param content
     * 返回收藏是否成功@return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ResponseBody
    @GetMapping(value = "/addCollection")
    public String addResourcesCollection(@RequestParam String content, HttpSession session) {
        Integer uId = (Integer) session.getAttribute("uId");
        UserCollection userCollection = new UserCollection();
        String[] split = content.split(":");
        userCollection.setC_content(split[0]);
        userCollection.setC_path(split[1]);
        userCollection.setC_foreign(uId + "");
        try {
            if (resourcesService.checkLove(split[1], uId + "") == null) {
                int i = resourcesService.addResourcesCollection(userCollection);
                if (i == 1) {
                    return "收藏成功";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "收藏重复了.......";
    }

    /**
     * 查询收藏表单,获取所有的收藏内容@return
     */
    @ResponseBody
    @GetMapping(value = "/getLoveMovie")
    public List<UserCollection> getLoveResources(HttpSession session) {
        Integer uId = (Integer) session.getAttribute("uId");
        List<UserCollection> loveResources = resourcesService.getLoveResources(uId + "");
        System.out.println("getLoveResources =" + loveResources);
        return loveResources;
    }

    /**
     * 传入所要查询的内容@param c_content
     * 返回符合条件的收藏列表@return
     */
    @ResponseBody
    @GetMapping(value = "/searchLoveMovie")
    public List<UserCollection> getSearchLoveMovie(@RequestParam String c_content, HttpSession session) {
        Integer uId = (Integer) session.getAttribute("uId");
        List<UserCollection> searchLoveMovie = resourcesService.getSearchLoveMovie(c_content, uId + "");
        System.out.println("getSearchLoveMovie =" + searchLoveMovie);
        return searchLoveMovie;
    }

    /**
     * 传入视频的路径,根据视频路径取消收藏@param content
     * 返回取消收藏是否成功@return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ResponseBody
    @GetMapping(value = "/deleteLoveMovie")
    public String deleteLoveMovie(@RequestParam String content, HttpSession session) {
        Integer uId = (Integer) session.getAttribute("uId");
        System.out.println("uId" + uId);
        UserCollection userCollection = new UserCollection();
        userCollection.setC_path(content);
        userCollection.setC_foreign(uId + "");
        int i = resourcesService.deleteLoveMovie(userCollection);
        if (i == 1) {
            return "取消收藏成功";
        }
        return "取消收藏失败了.......";
    }

    /**
     * HttpSession@param session
     * 个性化推荐@return
     */
    @ResponseBody
    @GetMapping(value = "/getRecommendMovie")
    public List<Resources> getRecommendResources(HttpSession session) {
        History history = resourcesService.getRlabel(session.getAttribute("uId") + "");
        List<Resources> recommendMovie = resourcesService.getRecommendMovie(history.getH_label());
        System.out.println("getRecommendResources =" + recommendMovie);
        return recommendMovie;
    }
}
