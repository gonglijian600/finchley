package com.dqqzj.io.sso.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author qzj 2017年10月13日
 */
@Data
@Entity
public class SysUser implements UserDetails,Serializable {
    @Id
    private String username;// 用户姓名
    private String nickname;//昵称
    private String email;//邮件
    private String photo;//头像
    private String password;// 登录密码
    private String role;// 角色
    @Column(name = "account_non_expired",columnDefinition="bool default true")
    private boolean accountNonExpired;
    @Column(name = "account_non_locked",columnDefinition="bool default true")
    private boolean accountNonLocked;
    @Column(name = "credentials_non_expired",columnDefinition="bool default true")
    private boolean credentialsNonExpired;
    @Column(name = "enabled",columnDefinition="bool default true")
    private boolean enabled;

    public SysUser(String username, String password, String role, boolean accountNonExpired, boolean accountNonLocked,
                   boolean credentialsNonExpired, boolean enabled) {
        // TODO Auto-generated constructor stub
        this.username = username;
        this.password = password;
        this.role = role;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }
    // 这是权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }


}
