package com.dqqzj.io.sso.security.kaptcha;

import com.dqqzj.io.sso.enums.HttpStatusEnum;
import com.dqqzj.io.sso.model.ResultResponseEntity;
import com.dqqzj.io.sso.security.constants.SecurityConstants;
import com.dqqzj.io.sso.utils.ResponseUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created on 2018/1/7 0007.
 *
 * @author zqj
 * @email 798078824@qq.com
 * @since 1.0
 */
@Component
public class KaptchaSecurityFilter extends OncePerRequestFilter implements InitializingBean {


    /**
     * 存放所有需要检验验证码得url
     */
    private List<String> urlList = new ArrayList<>();

    /**
     * 验证请求url与配置得url是否匹配得工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 初始化bena完成后该信息也就是拦截得url
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlList.add(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String url = validateUrl(request);
        if (url != null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码");
            String parameter_kaptcha_code = request.getParameter(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE);
            String session_kaptcha_code = (String) request.getSession().getAttribute(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE);
            if (Objects.equals(parameter_kaptcha_code, session_kaptcha_code)) {
                logger.info("验证码校验通过");
            } else {
                ResponseUtils.statusForJson(response,new ResultResponseEntity<>(HttpStatusEnum.CODE_ERRORT,null));
                return;
            }
        }
            filterChain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private String validateUrl(HttpServletRequest request) {
        String result = null;
        String requestUrl = request.getRequestURI();
        if (StringUtils.equalsIgnoreCase(request.getMethod(), HttpMethod.POST.name())) {
            for (String url : urlList) {
                if (pathMatcher.match(url, requestUrl)) {
                    result = url;
                }
            }
        }
        return result;
    }

}
