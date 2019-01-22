package com.dqqzj.io.zuul.config;
import com.dqqzj.io.zuul.handler.MySimpleUrlLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Auther: zqin
 * @Date: 10/01/2019 10:27
 * @Description:
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MySimpleUrlLogoutSuccessHandler mySimpleUrlLogoutSuccessHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
mySimpleUrlLogoutSuccessHandler.setDefaultTargetUrl("http://localhost:9060/logout");
        http

                .authorizeRequests()
                .antMatchers("/login","/logout")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutSuccessHandler(mySimpleUrlLogoutSuccessHandler)
                .logoutSuccessUrl("http://localhost:9060/logout")//此处应该使用handler来利用feign来声明式调用oauth的退出逻辑=流程，
                .deleteCookies("ZUUL")
                .and()
                .csrf()
                .disable();

    }

}
