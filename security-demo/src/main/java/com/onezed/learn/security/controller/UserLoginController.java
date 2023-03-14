package com.onezed.learn.security.controller;

import com.onezed.learn.security.common.ResponseResult;
import com.onezed.learn.security.model.vo.LoginVO;
import com.onezed.learn.security.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * description
 * </p>
 *
 * @author onezed
 */
@RestController
@RequestMapping("/v1.0")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @GetMapping("/hello")
    public String hello() {
        return "hello security !!";
    }

    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody LoginVO loginVO) {
        return userLoginService.login(loginVO);
    }

}
