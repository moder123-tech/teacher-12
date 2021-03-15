package cn.com.teacher.config;
import cn.com.teacher.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @author wx
 * @version 1.0
 * @date 2021/1/2 14:41
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {



    /**
     * addPathPatterns 添加拦截规则
     * excludePathPatterns 排除拦截规则
     *
     * 添加拦截器@param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html",
                "/signup.html","/css/*","/fonts/*","/images/*","/js/*","/login","/register","/send/*","/tes");
    }
}
