package com.dqqzj.io.sso.security.session;

import com.dqqzj.io.sso.enums.HttpStatusEnum;
import com.dqqzj.io.sso.model.ResultResponseEntity;
import com.dqqzj.io.sso.security.constants.SecurityConstants;
import com.dqqzj.io.sso.utils.Jacksons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: zqin
 * @Date: 11/01/2019 11:20
 * @Description:
 */
@Slf4j
public abstract class AbstractSessionStrategy {

    /**
     * 过期跳转的url
     */
    private String destinationUrl = SecurityConstants.DEFAULT_UNAUTHENTICATION_URL;

    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();



    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.session.InvalidSessionStrategy#
     * onInvalidSessionDetected(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("【AbstractSessionStrategy】 onSessionInvalid session is invalid");
        //跳转前是否创建新的session
        request.getSession();
        if ("XMLHttpRequest".equalsIgnoreCase((request).getHeader("X-Requested-With"))) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(Jacksons.parse(new ResultResponseEntity<>(HttpStatusEnum.SESSION_INVALID,null)));
        } else {
            log.info("redirect url is:" + destinationUrl);
            redirectStrategy.sendRedirect(request, response, destinationUrl);
        }

    }

}
