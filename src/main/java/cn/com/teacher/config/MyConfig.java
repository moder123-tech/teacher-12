package cn.com.teacher.config;

import cn.com.teacher.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


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
     * 添加拦截器@param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html",
                "/signup.html", "/css/*", "/fonts/*", "/images/*", "/js/*", "/login", "/register",
                "/send/*", "/test", "/getMovie", "/getTypeMovie/*", "/getTypeMovie", "/searchMovie", "/searchMovie/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*System.out.println("配置文件已经生效");*/
        registry.addResourceHandler("/static/image/**").addResourceLocations("file:F:\\Idea\\IdeaWork\\teacher\\src\\main\\resources\\static\\image\\");
    }

}
