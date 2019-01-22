package com.dqqzj.io.sso.utils;

import com.dqqzj.io.sso.enums.HttpStatusEnum;
import com.dqqzj.io.sso.model.ResultResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: zqin
 * @Date: 14/01/2019 09:49
 * @Description:
 */
public class ResponseUtils {
    public static void statusForJson(HttpServletResponse response,ResultResponseEntity resultResponseEntity) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(Jacksons.parse(resultResponseEntity));

    }
}
