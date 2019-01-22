package com.dqqzj.io.sso.security.mobile;

import com.dqqzj.io.sso.security.handle.MySavedRequestAwareAuthenticationSuccessHandler;
import com.dqqzj.io.sso.security.handle.MySimpleUrlAuthenticationFailureHandler;
import com.dqqzj.io.sso.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 短信登录配置
 * Created on 2018/1/10.
 *
 * @author zlf
 * @since 1.0
 */
@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    MySimpleUrlAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) {
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        smsAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(userDetailsService);
        //在UsernamePasswordAuthenticationFilter过滤前执行
        http.authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
