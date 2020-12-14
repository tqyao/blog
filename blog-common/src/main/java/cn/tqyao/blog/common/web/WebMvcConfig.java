///**
// * Copyright 2020-2030 The author personally reserves all rights.
// */
//package cn.tqyao.blog.common.web;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
///**
// * .<br>
// *
// * @author tanqinyao<br>
// * @version 1.0.0 <br>
// * @date Create in 2020/12/14 21:08 <br>
// */
//@Configuration
//@EnableWebMvc
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Bean
//    public StringHttpMessageConverter stringHttpMessageConverter() {
//        return new StringHttpMessageConverter();
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(stringHttpMessageConverter());
//    }
//
//}
