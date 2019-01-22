package com.dqqzj.io.zuul.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zqin
 * @Date: 11/12/2018 11:04
 * @Description:
 */
@RefreshScope
@RestController
public class ConfigClientController {

  //  @Value("${feign.compression.request.min-request-size}")
    private String size;

    @GetMapping("/size")
    public String getProfile() {
        return this.size;
    }

}
