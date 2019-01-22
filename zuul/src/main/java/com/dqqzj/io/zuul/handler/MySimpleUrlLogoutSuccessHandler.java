package com.dqqzj.io.zuul.handler;

import com.dqqzj.io.zuul.feign.OauthFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qzj on 2018/2/25
 */
@Slf4j
@Component
public class MySimpleUrlLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    OauthFeignClient oauthFeignClient;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("MySimpleUrlLogoutSuccessHandler#onLogoutSuccess is starting......");
        this.oauthFeignClient.logout();
        super.onLogoutSuccess(request, response, authentication);
        //自定义返回数据格式

    }

}
