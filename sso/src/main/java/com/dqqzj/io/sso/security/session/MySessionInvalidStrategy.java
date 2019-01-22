package com.dqqzj.io.sso.security.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: zqin
 * @Date: 11/01/2019 11:21
 * @Description:
 */
public class MySessionInvalidStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        onSessionInvalid(request, response);
    }
}

