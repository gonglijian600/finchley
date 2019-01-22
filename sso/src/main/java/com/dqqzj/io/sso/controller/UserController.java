package com.dqqzj.io.sso.controller;
import com.dqqzj.io.sso.entity.SysUser;
import com.dqqzj.io.sso.enums.HttpStatusEnum;
import com.dqqzj.io.sso.model.request.RegisterVO;
import com.dqqzj.io.sso.repository.UserRepository;
import com.dqqzj.io.sso.service.AutomaticLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.dqqzj.io.sso.security.constants.SecurityConstants.DEFAULT_REGISTER_URL;


/**
 * @Auther: zqin
 * @Date: 10/01/2019 10:52
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AutomaticLogin automaticLogin;

    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }
    @PutMapping(DEFAULT_REGISTER_URL)
    public ResponseEntity user(RegisterVO registerVO) {
        if (this.userRepository.findById(registerVO.getUsername()).isPresent()){
            throw new UsernameNotFoundException(HttpStatusEnum.ACCOUNT_EXISTED.getMessage());
        }else {
            SysUser user = new SysUser(registerVO.getUsername(),this.passwordEncoder.encode(registerVO.getPassword()),"ROLE_ADMIN",true,true,true,true);
            this.userRepository.save(user);
        }
       return ResponseEntity.ok(HttpStatusEnum.OK);
    }
}

