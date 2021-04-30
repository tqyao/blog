package cn.tqyao.blog.security.config;

import cn.tqyao.blog.security.dynamic.DynamicSecurityService;
import cn.tqyao.blog.security.filter.SecurityAuthenticationFilter;
import cn.tqyao.blog.security.filter.SecurityLoginAuthenticationFilter;
import cn.tqyao.blog.security.handle.JsonAuthenticationEntryPoint;
import cn.tqyao.blog.security.handle.JsonAuthenticationFailureHandler;
import cn.tqyao.blog.security.handle.JsonLoginSuccessHandler;

import cn.tqyao.blog.security.util.JWTTokenUtil;
import cn.tqyao.blog.security.util.RedisSecurityUtil;
import cn.tqyao.blog.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * .<br>
 * @author tanqinyao<br>
 * @date Create in 2020/11/13 20:52  <br>
 * @version 1.0.0 <br>
*/
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;


    /**
     * Web资源权限控制
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        //swagger-ui start
        web.ignoring().antMatchers("/doc.html");
        web.ignoring().antMatchers("/v2/**");
        web.ignoring().antMatchers("/v3/**");
        web.ignoring().antMatchers("/swagger**/**");
        web.ignoring().antMatchers("/webjars/**");
        //swagger-ui end

        //静态资源 start
        web.ignoring().antMatchers("/**/*.js");
        web.ignoring().antMatchers("/**/*.css");
        web.ignoring().antMatchers("/**/*.png");
        web.ignoring().antMatchers("/**/*.ico");
        //静态资源 start

        web.ignoring().antMatchers("/**/error");
    }


    /**
     * HTTP请求权限控制
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // /不需要保护的资源路径允许访问
        for (String url : ignoreUrlsConfig().getUrls ()) {
            http.authorizeRequests().antMatchers (url).permitAll ();
        }

        // 关闭跨站请求防护及不使用session
        http.cors().and()
                .csrf().disable()
                // 任何请求需要身份认证
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                //禁用session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .authenticationEntryPoint(jsonAuthenticationEntryPoint());

        http.addFilterBefore(securityLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(securityAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

//        if (dynamicSecurityService != null) {
//
//        }

    }
//
//    /**
//     * 配置密码加密策略
//     * @date 2020-11-015
//     * @param auth
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder());
//    }


    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RedisSecurityUtil redisSecurityUtil(){
        return new RedisSecurityUtil();
    }
    @Bean
    public JWTTokenUtil jwtTokenUtil(){
        return new JWTTokenUtil ();
    }
    @Bean
    public SecurityUtil securityUtil(){
        return new SecurityUtil();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JsonLoginSuccessHandler jsonLoginSuccessHandler(){
        return new JsonLoginSuccessHandler();
    }

    @Bean
    public JsonAuthenticationFailureHandler jsonAuthenticationFailureHandler(){
        return new JsonAuthenticationFailureHandler();
    }

    @Bean
    public JsonAuthenticationEntryPoint jsonAuthenticationEntryPoint(){
        return new JsonAuthenticationEntryPoint();
    }

    @Bean
    public SecurityLoginAuthenticationFilter securityLoginAuthenticationFilter() throws Exception {
        SecurityLoginAuthenticationFilter securityLoginAuthenticationFilter = new SecurityLoginAuthenticationFilter();
        securityLoginAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        securityLoginAuthenticationFilter.setAuthenticationSuccessHandler(jsonLoginSuccessHandler());
        securityLoginAuthenticationFilter.setAuthenticationFailureHandler(jsonAuthenticationFailureHandler());
        return securityLoginAuthenticationFilter;
    }

    @Bean
    public SecurityAuthenticationFilter securityAuthenticationFilter() throws Exception {
        return new SecurityAuthenticationFilter(authenticationManagerBean(), jsonAuthenticationEntryPoint());
    }

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig(){
        return new IgnoreUrlsConfig ();
    }

}
