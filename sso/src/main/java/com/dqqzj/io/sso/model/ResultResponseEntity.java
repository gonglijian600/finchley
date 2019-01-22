package com.dqqzj.io.sso.model;

import com.dqqzj.io.sso.enums.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;


/**
 * @Auther: zqin
 * @Date: 11/01/2019 11:37
 * @Description:
 */
@Data
@AllArgsConstructor
public class ResultResponseEntity<T> {

    private Integer code;
    private String message;
    private T data;
    public ResultResponseEntity(HttpStatusEnum httpStatusEnum,@Nullable T data){
        this.code = httpStatusEnum.getCode();
        this.message = httpStatusEnum.getMessage();
        this.data = data;
    }
}
