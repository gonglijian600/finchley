package com.dqqzj.io.zuul.feign;

import org.springframework.stereotype.Component;

/**
 * @Auther: zqin
 * @Date: 21/01/2019 14:02
 * @Description:
 */
@Component
public class OauthFeignFallback implements OauthFeignClient{
    @Override
    public void logout() {
        return;
    }
}
