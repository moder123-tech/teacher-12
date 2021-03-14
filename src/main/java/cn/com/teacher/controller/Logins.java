package cn.com.teacher.controller;

import cn.com.teacher.bean.UserInformation;
import cn.com.teacher.dao.UserDao;
import cn.com.teacher.service.UserService;
import cn.com.teacher.util.EmailSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLOutput;
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

    @Resource
    public UserService userService;



    @GetMapping(value = "/register")
    public String hello(UserInformation userInformation, Map map, ModelAndView modelAndView,@RequestParam("code") String code,HttpServletRequest request){
        userService.insertUser(userInformation);
        HttpSession sessoin=request.getSession();
        Object codes = sessoin.getAttribute("codes");
        System.out.println(codes);
        System.out.println(code);
        if(!(codes.equals(code))){
            return "redirect:http://localhost:8080/error.html";
        }
        return "redirect:http://localhost:8080/login.html";
    }

    @ResponseBody
    @RequestMapping(value = "/send/{emails}")
    public String sendEmail(UserInformation userInformation, Map map, @PathVariable("emails") String emails,HttpServletRequest request) throws InterruptedException {
        new EmailSend(userInformation,map,emails,javaMailSender,emailProvider,request);
        return "200";
    }


}
