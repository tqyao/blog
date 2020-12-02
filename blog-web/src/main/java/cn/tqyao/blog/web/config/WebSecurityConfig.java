/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.config;

import cn.tqyao.blog.entity.Member;
import cn.tqyao.blog.security.config.SecurityConfig;
import cn.tqyao.blog.web.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;


/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/15 11:52 <br>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends SecurityConfig {

    @Autowired
    private IMemberService memberService;

//    @Bean
//    public UserDetailsService userDetailsService(){
//        //获取用户信息
//        return (username) -> {
//            Member member = memberService.loadUserByUsername(username);
//            return new CustomizeUserDetails(member.getId(), member.getUsername(),
//                    passwordEncoder.encode(member.getPassword()),
//                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
//        };
//    }

    @Bean
    public UserDetailsService userDetailsService(){
        //获取用户信息
        return (username) -> memberService.loadUserByUsername(username);
    }
}
