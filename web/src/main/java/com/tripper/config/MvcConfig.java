package com.tripper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author HanJiyoung
 * url 매핑하는 mvc 설정 클래스
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("user");
//        registry.addViewController("/signup").setViewName("signup");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/admin").setViewName("admin");
//        registry.addViewController("/user/list").setViewName("userList");
//        registry.addViewController("/board/list").setViewName("boardList");
//        registry.addViewController("/board/post").setViewName("boardPost");
    }
}