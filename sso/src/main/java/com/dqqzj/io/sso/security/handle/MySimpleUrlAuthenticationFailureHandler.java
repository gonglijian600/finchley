package com.dqqzj.io.sso.security.handle;

import com.dqqzj.io.sso.enums.HttpStatusEnum;
import com.dqqzj.io.sso.model.ResultResponseEntity;
import com.dqqzj.io.sso.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录失败处理
 * Created by qzj on 2018/2/2
 */
@Slf4j
@Component
public class MySimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        logger.error("【MySimpleUrlAuthenticationFailureHandler】 onAuthenticationFailure AuthenticationException",exception);
        ResponseUtils.statusForJson(response,new ResultResponseEntity<>(HttpStatusEnum.ACCOUNT_ERROR,exception));
    }
}
