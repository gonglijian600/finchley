package com.dqqzj.io.zuul.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: zqin
 * @Date: 21/01/2019 14:01
 * @Description:
 */
@FeignClient(name = "oauth",fallback = OauthFeignFallback.class)
public interface OauthFeignClient {
    @RequestMapping(value = "/oauth2.0/logout", method = RequestMethod.GET)
    void logout();

}
