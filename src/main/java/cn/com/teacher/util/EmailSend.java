package cn.com.teacher.util;

import cn.com.teacher.bean.UserInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author wx
 * @version 1.0
 * @date 2021/3/12 21:09
 */
@Slf4j
public class EmailSend {

    public EmailSend() {

    }

    public EmailSend(UserInformation userInformation, Map map, String emails, JavaMailSender javaMailSender, String emailProvider, HttpServletRequest request) {
        sendEmail(userInformation, map, emails, javaMailSender, emailProvider, request);
    }

    public String sendEmail(UserInformation userInformation, Map map, String emails, JavaMailSender javaMailSender, String emailProvider, HttpServletRequest request) {
        String[] letters = new String[]{
                "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m",
                "A", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String stringBuilder = "";
        for (int i = 0; i < 6; i++) {
            stringBuilder = stringBuilder + letters[(int) Math.floor(Math.random() * letters.length)];
        }
        System.out.println(stringBuilder);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailProvider);
        simpleMailMessage.setSubject("验证码");
        simpleMailMessage.setText("验证码为:" + stringBuilder);
        simpleMailMessage.setTo(emails);
        javaMailSender.send(simpleMailMessage);
        HttpSession sessoin = request.getSession();
        sessoin.setAttribute("codes", stringBuilder);
        log.info("成功");
        return "200";
    }
}
