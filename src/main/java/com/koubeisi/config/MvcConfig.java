package com.koubeisi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/18 14:38
 * @Version 1.0
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/registerpage").setViewName("register");
        registry.addViewController("/otppage").setViewName("otp");
        registry.addViewController("/loginpage").setViewName("login");
        registry.addViewController("/createitempage").setViewName("createitem");
        registry.addViewController("/listitempage").setViewName("listitem");
        registry.addViewController("/itemdetailpage").setViewName("itemdetail");
    }
}
