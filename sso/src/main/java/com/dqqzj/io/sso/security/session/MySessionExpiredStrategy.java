package com.dqqzj.io.sso.security.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * @Auther: zqin
 * @Date: 11/01/2019 11:20
 * @Description:
 */
public class MySessionExpiredStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {

        onSessionInvalid(event.getRequest(), event.getResponse());
    }

}
