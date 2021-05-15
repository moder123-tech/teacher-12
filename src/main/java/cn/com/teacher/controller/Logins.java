package cn.com.teacher.controller;

import cn.com.teacher.bean.UserInformation;
import cn.com.teacher.service.UserService;
import cn.com.teacher.util.EmailSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private BCryptPasswordEncoder passwordEncoder;

    public Logins() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * HttpServletResponse@param response
     * 传入所要注册的用户对象@param userInformation
     * map集合@param map
     * 模型视图@param modelAndView
     * 状态@param code
     * httprequest@param request
     * 返回成功或者失败页面@return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @GetMapping(value = "/register")
    public String register(UserInformation userInformation, Map map, ModelAndView modelAndView, @RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newPassword = passwordEncoder.encode(userInformation.getU_password());
        response.setContentType("text/html;charset=utf-8");
        userInformation.setU_password(newPassword);
        userInformation.setU_state("0");
        userInformation.setU_visual("1");
        UserInformation user = userService.getUser(userInformation.getU_number(), "1");
        HttpSession sessoin = request.getSession();
        Object codes = sessoin.getAttribute("codes");
        if (!(codes.equals(code))) {
            response.getWriter().write("<script>alert('验证码不正确!');window.location='signup.html'; </script>");
        }else {
            if (user == null) {
                userService.insertUser(userInformation);
            } else {
                response.getWriter().write("<script>alert('账号已存在,请登录!');window.location='login.html'; </script>");
            }
            response.getWriter().write("<script>alert('注册成功,请登录！');window.location='login.html'; </script>");
        }
        return null;
    }

    /**
     * HttpServletResponse@param response
     * 传入所要注册的用户对象@param userInformation
     * map集合@param map
     * 模型视图@param modelAndView
     * 状态@param code
     * httprequest@param request
     * 返回成功或者失败页面@return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @GetMapping(value = "/find")
    public String findPassword(UserInformation userInformation, Map map, ModelAndView modelAndView, @RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newPassword = passwordEncoder.encode(userInformation.getU_password());
        response.setContentType("text/html;charset=utf-8");
        userInformation.setU_password(newPassword);
        UserInformation user = userService.getUser(userInformation.getU_number(), "1");
        if (user == null) {
            response.getWriter().write("<script>alert('该账号不存在,请注册!');window.location='findPass.html'; </script>");
        } else {
            userService.updateUser(userInformation);
        }
        HttpSession sessoin = request.getSession();
        Object codes = sessoin.getAttribute("codes");
        if (!(codes.equals(code))) {
            response.getWriter().write("<script>alert('验证码不正确!');window.location='findPass.html'; </script>");
        }
        response.getWriter().write("<script>alert('修改密码成功,请登录！');window.location='login.html'; </script>");
        return null;
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
     * HttpServletResponse@param response
     * 用户所填的账号信息@param u_number
     * 用户所填的密码信息@param u_password
     * HttpSession@param session
     * 返回成功或者失败页面@return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @GetMapping(value = "/login")
    public String login(@RequestParam("eamil") String u_number, @RequestParam("password") String u_password, HttpSession session, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("text/html;charset=utf-8");
            UserInformation user = userService.getUser(u_number, "1");
            boolean matches = passwordEncoder.matches(u_password, user.getU_password());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String u_time = df.format(new Date());
            Integer u_id = user.getU_id();
            String uName = user.getU_name();
            session.setAttribute("uName", uName);
            session.setAttribute("email", u_number);
            session.setAttribute("uId", u_id);
            if (matches == true && user.getU_state().equals("0")) {
                session.setAttribute("msg", "200");
                userService.updateUserTime(u_number, u_time);
                return "redirect:http://localhost:8080/index.html";
            }
            if (matches == true && user.getU_state().equals("1")) {
                session.setAttribute("msg", "200");
                userService.updateUserTime(u_number, u_time);
                return "redirect:http://localhost:8080/administrators.html";
            }
            if (matches == true && user.getU_state().equals("2")) {
                session.setAttribute("msg", "200");
                userService.updateUserTime(u_number, u_time);
                return "redirect:http://localhost:8080/superAdministrator.html";
            }
            response.getWriter().write("<script>alert('账号或者密码不正确!');window.location='login.html'; </script>");
        } catch (Exception exception) {
            response.getWriter().write("<script>alert('账号或者密码不正确!');window.location='login.html'; </script>");
        }
        return null;
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
        System.out.println("---------------");
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        String email = (String) session.getAttribute("email");
        UserInformation user = userService.getUser(email, "1");
        String uuidFilename = email + System.currentTimeMillis();
        File fileDir = new File("F:/Idea/IdeaWork/teacher/src/main/resources/static/image/" + email);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        //创建新的文件夹
        File newFile = new File("F:/Idea/IdeaWork/teacher/src/main/resources/static/image/" + email, uuidFilename + ".jpg");
        file.transferTo(newFile);
        UserInformation userInformation = new UserInformation();
        userInformation.setU_number(email);
        userInformation.setU_head(uuidFilename + ".jpg");
        int i = userService.updateUpdateUploadImage(userInformation);
        if (i == 1) {
            if (user.getU_state().equals("0")) {
                session.setAttribute("msg", "200");
                return "redirect:http://localhost:8080/personalCenter.html";
            }
            if (user.getU_state().equals("1")) {
                session.setAttribute("msg", "200");
                return "redirect:http://localhost:8080/administrators.html";
            }
            if (user.getU_state().equals("2")) {
                session.setAttribute("msg", "200");
                return "redirect:http://localhost:8080/administrator.html";
            }
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
        UserInformation userInformation = new UserInformation();
        String email = (String) session.getAttribute("email");
        userInformation.setU_head(userService.getUserImage(email));
        userInformation.setU_number(email);
        return userInformation;
    }

    /**
     * 传入用户所修改的昵称@param names
     * 传入用户所修改的密码@param pass
     * HttpSession@param session
     * 返回修改是否成功@return
     */
    @ResponseBody
    @GetMapping(value = "/updateData")
    public String updateData(@RequestParam String u_name, String u_password, HttpSession session) {
        String u_number = (String) session.getAttribute("email");
        String newPassword = passwordEncoder.encode(u_password);
        int i = userService.updateUserInformation(u_name, newPassword, u_number);
        if (i == 1) {
            return "修改成功,请重新登录!";
        }
        return "修改失败!";
    }

    /**
     * HttpSession@param session
     * 查询用户表中的除自己以外的所有信息@return
     */
    @ResponseBody
    @GetMapping(value = "/showAllUsers")
    public List<UserInformation> showAllUsers(HttpSession session) {
        String u_number = (String) session.getAttribute("email");
        List<UserInformation> userInformations = userService.selectAllUsers(u_number);
        System.out.println("userInformations" + userInformations);
        return userInformations;
    }

    /**
     * HttpSession@param session
     * 查询用户表中的除自己以外的所有信息@return
     */
    @ResponseBody
    @GetMapping(value = "/selectSomeUser")
    public List<UserInformation> selectSomeUser(HttpSession session, @RequestParam("u_number") String u_number, @RequestParam("u_update_number") String u_update_number) {
        UserInformation userInformation = new UserInformation();
        if (u_update_number.equals("无")) {
            System.out.println("-------");
        } else {
            userInformation.setU_update_number(u_update_number);
        }
        userInformation.setU_number(u_number);
        userInformation.setU_update_time((String) session.getAttribute("email"));
        List<UserInformation> userInformations = userService.searchSomeUser(userInformation);
        System.out.println("userInformations" + userInformations);
        return userInformations;
    }

    /**
     * HttpSession@param session
     * 查询用户表中的信息@return
     */
    @ResponseBody
    @GetMapping(value = "/showSomeUser")
    public List<UserInformation> showSomeUser(HttpSession session, @RequestParam String u_update_number) {
        String u_number = (String) session.getAttribute("email");
        List<UserInformation> userInformations = userService.selectSomeUser(u_number, u_update_number);
        System.out.println("userInformations" + userInformations);
        return userInformations;
    }

    /**
     * HttpSession@param session
     * 传入要删除的用户账号@param u_number
     * 返回是否删除成功@return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ResponseBody
    @GetMapping(value = "/deleteUser")
    public List<UserInformation> deleteUser(@RequestParam String u_number, HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String u_update_time = df.format(new Date());
        UserInformation userInformation = new UserInformation();
        userInformation.setU_number(u_number);
        userInformation.setU_update_number((String) session.getAttribute("email"));
        userInformation.setU_update_time(u_update_time);
        int i = userService.upUser(userInformation);
        List<UserInformation> userInformations = this.showAllUsers(session);
        return userInformations;
    }

    /**
     * HttpSession@param session
     * 传入要删除的用户账号@param u_number
     * 返回是否删除成功@return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ResponseBody
    @GetMapping(value = "/updateUser")
    public List<UserInformation> deleteUser(@RequestParam String u_number, @RequestParam String u_password, @RequestParam String u_state, HttpSession session) {
        String newPassword = passwordEncoder.encode(u_password);
        if(u_password.equals("000000")){
            newPassword=null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String u_update_time = df.format(new Date());
        String u_update_number = (String) session.getAttribute("email");
        userService.updateUsers(u_number, newPassword, u_state, u_update_number, u_update_time);
        List<UserInformation> userInformations = this.showAllUsers(session);
        return userInformations;
    }

    /**
     * 返回下拉列表对象@return
     */
    @ResponseBody
    @GetMapping(value = "/showSelectUser")
    public List<UserInformation> showSelectUser() {
        UserInformation userInformation = new UserInformation();
        List<UserInformation> userInformations = userService.showSelectUser(userInformation);
        return userInformations;
    }

    /**
     * HttpSession@param session
     * 查找自己所删除的用户@return
     */
    @ResponseBody
    @GetMapping(value = "/showDeleUser")
    public List<UserInformation> showDeleUser(HttpSession session) {
        UserInformation userInformation = new UserInformation();
        userInformation.setU_update_number((String) session.getAttribute("email"));
        userInformation.setU_visual("0");
        List<UserInformation> userInformations = userService.showSelectUser(userInformation);
        return userInformations;
    }

    /**
     * HttpSession@param session
     * 管理员查看已被删除账号@return
     */
    @ResponseBody
    @GetMapping(value = "/seeDeleUser")
    public List<UserInformation> SeeDeleUser(HttpSession session) {
        UserInformation userInformation = new UserInformation();
        userInformation.setU_visual("0");
        List<UserInformation> userInformations = userService.showSelectUser(userInformation);
        return userInformations;
    }

    /**
     *
     * HttpSession@param session
     * 恢复所删除的账号@return
     */
    @ResponseBody
    @GetMapping(value = "/recoveryUser")
    public List<UserInformation> recoveryUser(HttpSession session,@RequestParam String u_number) {
        userService.recoveryUser(u_number);
        List<UserInformation> userInformations = this.showDeleUser(session);
        return userInformations;
    }

}
