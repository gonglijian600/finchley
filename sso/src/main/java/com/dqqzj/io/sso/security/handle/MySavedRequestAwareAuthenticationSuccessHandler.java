package com.dqqzj.io.sso.security.handle;

import com.dqqzj.io.sso.enums.HttpStatusEnum;
import com.dqqzj.io.sso.model.ResultResponseEntity;
import com.dqqzj.io.sso.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * * 自定义登录成功处理
 * Created by qzj on 2018/2/2
 */
@Slf4j
@Component
public class MySavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * 登录成功处理器
     * @param request
     * @param response
     * @param authentication
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        logger.info("【MySavedRequestAwareAuthenticationSuccessHandler】 onAuthenticationSuccess authentication={"+authentication.toString()+"}");
        ResponseUtils.statusForJson(response,new ResultResponseEntity<>(HttpStatusEnum.OK,authentication));
    }
}
