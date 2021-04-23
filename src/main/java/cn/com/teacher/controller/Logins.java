package cn.com.teacher.controller;

import cn.com.teacher.bean.UserInformation;
import cn.com.teacher.service.UserService;
import cn.com.teacher.util.EmailSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author wx
 * @version 1.0
 * @date 2021/3/7 12:26
 */
@Controller
@Slf4j
public class Logins {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailProvider;

    @Autowired
    public UserService userService;


    /**
     * 传入所要注册的用户对象@param userInformation
     * map集合@param map
     * 模型视图@param modelAndView
     * 状态@param code
     * httprequest@param request
     * 返回成功或者失败页面@return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @GetMapping(value = "/register")
    public String register(UserInformation userInformation, Map map, ModelAndView modelAndView, @RequestParam("code") String code, HttpServletRequest request) {
        userService.insertUser(userInformation);
        HttpSession sessoin = request.getSession();
        Object codes = sessoin.getAttribute("codes");
        if (!(codes.equals(code))) {
            return "redirect:http://localhost:8080/number.html";
        }
        return "redirect:http://localhost:8080/login.html";
    }

    /**
     * 传入所要注册的用户对象@param userInformation
     * map集合@param map
     * 从前端页面获取用户所填的邮箱@param emails
     * httprequest@param request
     * 返回状态码 200:成功@return
     */
    @ResponseBody
    @RequestMapping(value = "/send/{emails}")
    public String sendEmail(UserInformation userInformation, Map map, @PathVariable("emails") String emails, HttpServletRequest request) throws InterruptedException {
        new EmailSend(userInformation, map, emails, javaMailSender, emailProvider, request);
        return "200";
    }

    /**
     * 用户所填的账号信息@param u_number
     * 用户所填的密码信息@param u_password
     * HttpSession@param session
     * 返回成功或者失败页面@return
     */
    @GetMapping(value = "/login")
    public String login(@RequestParam("eamil") String u_number, @RequestParam("password") String u_password, HttpSession session) {
        try {
            UserInformation user = userService.getUser(u_number, u_password);
            Integer u_id = user.getU_id();
            String uName = user.getU_name();
            session.setAttribute("uName", uName);
            session.setAttribute("email", u_number);
            session.setAttribute("uId",u_id);
            if (uName != null) {
                session.setAttribute("msg", "200");
                return "redirect:http://localhost:8080/index.html";
            }
            return "redirect:http://localhost:8080/password.html";
        } catch (Exception exception) {
            return "redirect:http://localhost:8080/password.html";
        }


    }

    /**
     * HttpSession@param session
     * 返回昵称信息@return
     */
    @ResponseBody
    @GetMapping(value = "/test")
    public String test(HttpSession session) {
        return (String) session.getAttribute("uName");
    }

    /**
     * 前端传过来的信息@param data
     * HttpSession@param session
     * 返回上传头像是否成功@return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @PostMapping(value = "/uploadImage")
    public String updateUploadImage(MultipartFile file, HttpSession session) throws IOException {
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        String email = (String) session.getAttribute("email");
        String uuidFilename = email+System.currentTimeMillis();
        File fileDir = new File("F:/Idea/IdeaWork/teacher/src/main/resources/static/image/"+email);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        //创建新的文件夹
        File newFile = new File("F:/Idea/IdeaWork/teacher/src/main/resources/static/image/"+email,uuidFilename+".jpg");
        file.transferTo(newFile);
        UserInformation userInformation = new UserInformation();
        userInformation.setU_number(email);
        userInformation.setU_head(uuidFilename+".jpg");
        int i = userService.updateUpdateUploadImage(userInformation);
        if (i == 1) {
            return "redirect:http://localhost:8080/index.html";
        }
        return "上传头像失败";
    }

    /**
     * HttpSession@param session
     * 返回用户的头像信息@return
     */
    @ResponseBody
    @GetMapping(value = "/getUserImage")
    public UserInformation getUserImage(HttpSession session) {
        UserInformation userInformation=new UserInformation();
        String email =(String) session.getAttribute("email");
        userInformation.setU_head(userService.getUserImage(email));
        userInformation.setU_number(email);
        return userInformation;
    }

    /**
     *
     * 传入用户所修改的昵称@param names
     * 传入用户所修改的密码@param pass
     * HttpSession@param session
     * 返回修改是否成功@return
     */
    @ResponseBody
    @GetMapping(value = "/updateData")
    public String updateData(@RequestParam String u_name, String u_password, HttpSession session) {
        String u_number =(String) session.getAttribute("email");
        int i = userService.updateUserInformation(u_name, u_password,u_number);
        if(i==1){
            return "修改成功,请重新登录!";
        }
        return "修改失败!";
    }
}
