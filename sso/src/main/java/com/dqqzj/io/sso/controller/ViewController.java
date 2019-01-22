package com.dqqzj.io.sso.controller;

import com.dqqzj.io.sso.security.constants.SecurityConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: zqin
 * @Date: 10/01/2019 13:17
 * @Description:
 */
@Controller
public class ViewController {

    @GetMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    public String login(){
        return "login";
    }
    @GetMapping(SecurityConstants.DEFAULT_REGISTER_URL)
    public String register(){
        return "register";
    }

}
