package com.dqqzj.io.zuul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Auther: zqin
 * @Date: 16/01/2019 14:54
 * @Description:
 */
@RestController
public class OauthClientController {
    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
    @GetMapping("/client")
    public String client() {
        return "client";
    }

}
