package com.dqqzj.io.sso.security.mobile;

import com.aliyuncs.exceptions.ClientException;
import com.dqqzj.io.sso.aliyun.AliyunSMS;
import com.dqqzj.io.sso.security.constants.SecurityConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by qzj on 2018/2/26
 */
@RestController
public class SmsSendCodeController {

    @GetMapping(SecurityConstants.DEFAULT_MOBILE_CODE_URL_PREFIX)
    public String sendSms(String phone){
        String status = "";
        try {
            status = new AliyunSMS().send(phone);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return status;
    }
}
