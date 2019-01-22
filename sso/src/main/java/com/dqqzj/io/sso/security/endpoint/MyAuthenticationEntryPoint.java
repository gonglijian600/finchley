package com.dqqzj.io.sso.security.endpoint;

import com.dqqzj.io.sso.model.ResultResponseEntity;
import com.dqqzj.io.sso.security.constants.SecurityConstants;
import com.dqqzj.io.sso.utils.Jacksons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dqqzj.io.sso.enums.HttpStatusEnum.AUTHENTICATION_ERROR;

/**
 * @Auther: zqin
 * @Date: 11/01/2019 15:37
 * @Description:
 */
@Slf4j
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private AntPathMatcher pathMatcher = new AntPathMatcher();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.info("【MyAuthenticationEntryPoint】请求路径为："+request.getRequestURI());
        if ("XMLHttpRequest".equalsIgnoreCase((request).getHeader("X-Requested-With"))) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(Jacksons.parse(new ResultResponseEntity<>(AUTHENTICATION_ERROR,authException)));
        } else if (pathMatcher.match("/oauth/authorize",request.getRequestURI())){
            log.info("redirect url is:" + SecurityConstants.DEFAULT_UNAUTHENTICATION_URL);
            redirectStrategy.sendRedirect(request, response, SecurityConstants.DEFAULT_UNAUTHENTICATION_URL);
        }
    }
}

