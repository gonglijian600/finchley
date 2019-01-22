package com.dqqzj.io.sso.security.kaptcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/1/10.
 *
 * @author zlf
 * @since 1.0
 */
@Component
public class KaptchaSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private KaptchaSecurityFilter kaptchaSecurityFilter;

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(kaptchaSecurityFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }

}
