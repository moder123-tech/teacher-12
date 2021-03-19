package cn.com.teacher.controller;

import cn.com.teacher.bean.Resources;
import cn.com.teacher.bean.UName;
import cn.com.teacher.bean.UserInformation;
import cn.com.teacher.service.ResourcesService;
import cn.com.teacher.service.UserService;
import cn.com.teacher.util.EmailSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
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






    @GetMapping(value = "/register")
    public String register(UserInformation userInformation, Map map, ModelAndView modelAndView,@RequestParam("code") String code,HttpServletRequest request){
        userService.insertUser(userInformation);
        HttpSession sessoin=request.getSession();
        Object codes = sessoin.getAttribute("codes");
        if(!(codes.equals(code))){
            return "redirect:http://localhost:8080/number.html";
        }
        return "redirect:http://localhost:8080/login.html";
    }

    @ResponseBody
    @RequestMapping(value = "/send/{emails}")
    public String sendEmail(UserInformation userInformation, Map map, @PathVariable("emails") String emails,HttpServletRequest request) throws InterruptedException {
       new EmailSend(userInformation,map,emails,javaMailSender,emailProvider,request);
        return "200";
    }

    @GetMapping(value = "/login")
    public String login(@RequestParam("eamil") String u_number, @RequestParam("password") String u_password, HttpSession session){
        try {
            String number = userService.getUser(u_number, u_password);
            session.setAttribute("number",number);
            if(number+""!=null){
                session.setAttribute("msg","200");
                return "redirect:http://localhost:8080/index.html";
            }
            return "redirect:http://localhost:8080/password.html";
        }catch (Exception exception){
            return "redirect:http://localhost:8080/password.html";
        }


    }
    @ResponseBody
    @GetMapping(value = "/test")
    public String test(HttpSession session){
        return (String) session.getAttribute("number");
    }



}
