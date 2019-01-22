package com.dqqzj.io.sso.controller;

import com.dqqzj.io.sso.model.ResultResponseEntity;
import com.dqqzj.io.sso.security.constants.SecurityConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.dqqzj.io.sso.enums.HttpStatusEnum.AUTHENTICATION_STARTING;


/**
 * Created by qzj on 2018/3/8
 */
@RestController
@SessionAttributes({"authorizationRequest"})
public class OAuthController {

    @RequestMapping({ "/oauth2.0/confirm_access"})
    public ResponseEntity getAccessConfirmation(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, String> mapScope = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes"));
        Set<String> scopes = new HashSet<>();
        mapScope.forEach((k,v) -> scopes.add(k));
        return ResponseEntity.ok(new ResultResponseEntity<>(AUTHENTICATION_STARTING,scopes));
    }

    /**
     * @description:  用于渲染授权服务器的错误，采用自定义的需要进行AuthorizationServerEndpointsConfigurer配置
     * @params: [model, request]
     * @return: java.lang.String
     * @auther: zqin
     * @date: 14/01/2019 11:33
     */

    @RequestMapping({ "/oauth2.0/error" })
    public String handleError(Map<String, Object> model, HttpServletRequest request) {
        Object error = request.getAttribute("error");
        String errorSummary;
        if (error instanceof OAuth2Exception) {
            OAuth2Exception oauthError = (OAuth2Exception) error;
            errorSummary = HtmlUtils.htmlEscape(oauthError.getSummary());
        } else {
            errorSummary = "Unknown error";
        }
        model.put("errorSummary", errorSummary);
        return "oauth_error";
    }
//    @RequestMapping("/oauth/confirm_access")
//    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
//        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
//        ModelAndView view = new ModelAndView();
//        view.setViewName("base-grant");
//        view.addObject("clientId", authorizationRequest.getClientId());
//        return view;
//    }

    @GetMapping(SecurityConstants.DEFAULT_LOGOUT_URL)
    public void exit(HttpServletRequest request, HttpServletResponse response) {
        // token can be revoked here if needed
        new SecurityContextLogoutHandler().logout(request, null, null);
//        try {
//            //sending back to client app
//            response.sendRedirect(request.getHeader("referer"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}