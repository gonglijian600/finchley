package com.dqqzj.io.sso.config;
import com.dqqzj.io.sso.security.constants.SecurityConstants;
import com.dqqzj.io.sso.security.constants.SessionConstants;
import com.dqqzj.io.sso.security.handle.MyLogoutSuccessHandler;
import com.dqqzj.io.sso.security.handle.MySimpleUrlAuthenticationFailureHandler;
import com.dqqzj.io.sso.security.kaptcha.KaptchaSecurityConfig;
import com.dqqzj.io.sso.security.mobile.SmsAuthenticationSecurityConfig;
import com.dqqzj.io.sso.security.session.MySessionExpiredStrategy;
import com.dqqzj.io.sso.security.session.MySessionInvalidStrategy;
import com.dqqzj.io.sso.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * @Auther: zqin
 * @Date: 10/01/2019 10:50
 * @Description:
 */

@Configuration
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    KaptchaSecurityConfig kaptchaSecurityConfig;

    @Autowired
    SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;

    @Autowired
    MySimpleUrlAuthenticationFailureHandler mySimpleUrlAuthenticationFailureHandler;
    @Autowired
    MyLogoutSuccessHandler myLogoutSuccessHandler;
    private InvalidSessionStrategy invalidSessionStrategy = new MySessionInvalidStrategy();

    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy = new MySessionExpiredStrategy();

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers("/logout","/oauth2.0/**","/oauth/authorize", SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                //.successHandler(mySavedRequestAwareAuthenticationSuccessHandler)
                .failureHandler(mySimpleUrlAuthenticationFailureHandler)
                .permitAll()
                .and()
                .apply(kaptchaSecurityConfig)
                .and()
                .apply(smsAuthenticationSecurityConfig)
                .and()
                .rememberMe()
                //.userDetailsService(customUserDetailsService)
                //.tokenRepository(persistentTokenRepository())
                .key("secret")
//                .rememberMeCookieName("remember-me")
//                .rememberMeParameter("remember-me")
                //.tokenValiditySeconds(604800)
                .and()
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(SessionConstants.maximumSessions)//最大session并发数量1
                .maxSessionsPreventsLogin(SessionConstants.maxSessionsPreventsLogin)//之后的登录踢掉之前的登录
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
                .logout()
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .deleteCookies("OAUTH")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/oauth2.0/**","/favicon.ico","/css/**","/fonts/**","/images/**","/js/**");
    }

}
