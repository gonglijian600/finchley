package com.dqqzj.io.sso.security.mobile;

import com.aliyuncs.exceptions.ClientException;
import com.dqqzj.io.sso.aliyun.AliyunSMS;
import com.dqqzj.io.sso.enums.HttpStatusEnum;
import com.dqqzj.io.sso.security.constants.SecurityConstants;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 短信登录过滤器
 * Created on 2018/1/10.
 *
 * @author zlf
 * @since 1.0
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * request中必须含有mobile参数
     */
    private String mobileParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
    /**
     * post请求
     */
    private boolean postOnly = true;

    protected SmsAuthenticationFilter() {

        /**
         * 处理的手机验证码登录请求处理url
         */
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE , HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //判断是是不是post请求
        if (postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //从请求中获取手机号码
        String mobile = request.getParameter(mobileParameter);
        String sms_code = request.getParameter(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS);

        //进行验证码对比校验
        try {
            String query_code = new AliyunSMS().query(mobile);
            if (Objects.equals(sms_code,query_code)) {
                throw new AuthenticationServiceException(HttpStatusEnum.CODE_ERRORT.getMessage());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        //创建SmsCodeAuthenticationToken(未认证)
        SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile);
        //设置用户信息
        setDetails(request, authRequest);
        //返回Authentication实例
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}
