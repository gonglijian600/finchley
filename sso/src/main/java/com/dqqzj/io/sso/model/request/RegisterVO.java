package com.dqqzj.io.sso.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author qinzhongjian
 * @date created in 2018/7/27 12:51
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterVO {
    private static final long serialVersionUID = -7019570768557438079L;

    @NotNull
    @Pattern(regexp = "^\\d{11}$", message = "请输入11位手机号")
    @JsonProperty("username")
    private String username;

    @NotNull
    @Size(min = 6, max = 20, message = "请输入6~20位的密码")
    @JsonProperty("password")
    private String password;

}
