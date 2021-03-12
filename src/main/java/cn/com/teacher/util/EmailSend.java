package cn.com.teacher.util;

import cn.com.teacher.bean.UserInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author wx
 * @version 1.0
 * @date 2021/3/12 21:09
 */
@Slf4j
public class EmailSend {



    public EmailSend(){

    }

    public EmailSend(UserInformation userInformation, Map map,String emails, JavaMailSender javaMailSender, String emailProvider){
        sendEmail(userInformation,map,emails,javaMailSender,emailProvider);
    }

    public  String sendEmail(UserInformation userInformation, Map map,String emails,JavaMailSender javaMailSender,String emailProvider){
        String[] letters = new String[] {
                "q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m",
                "A","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M",
                "0","1","2","3","4","5","6","7","8","9"};
        String stringBuilder ="";
        for (int i = 0; i < 6; i++) {
            stringBuilder = stringBuilder + letters[(int)Math.floor(Math.random()*letters.length)];
        }
        System.out.println(stringBuilder);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailProvider);
        System.out.println(emailProvider);
        simpleMailMessage.setSubject("验证码");
        simpleMailMessage.setText("验证码为:"+stringBuilder);
        simpleMailMessage.setTo(emails);
        javaMailSender.send(simpleMailMessage);
        map.put("code",stringBuilder);
        log.info("成功");
        return "200";
    }
}
