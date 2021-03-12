package cn.com.teacher.controller;

import cn.com.teacher.bean.UserInformation;
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
import java.sql.SQLOutput;
import java.util.Map;

/**
 * @author wx
 * @version 1.0
 * @date 2021/3/7 12:26
 */
@RestController
@Slf4j
public class login {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailProvider;


    @RequestMapping("/login")
    public String hello(UserInformation userInformation, Map map, ModelAndView modelAndView){
        System.out.println(userInformation);
        return "login";
    }

    @RequestMapping(value = "/send/{emails}")
    public String sendEmail(UserInformation userInformation, Map map, @PathVariable("emails") String emails) throws InterruptedException {
        new EmailSend(userInformation,map,emails,javaMailSender,emailProvider);
        return "200";
    }

    @RequestMapping("/test")
    public String hello(){

        return "200";
    }

}
