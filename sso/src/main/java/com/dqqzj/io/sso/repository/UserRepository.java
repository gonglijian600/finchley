package com.dqqzj.io.sso.repository;

import com.dqqzj.io.sso.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zqin
 * @Date: 10/01/2019 11:01
 * @Description:
 */
public interface UserRepository extends JpaRepository<SysUser,String> {
}
