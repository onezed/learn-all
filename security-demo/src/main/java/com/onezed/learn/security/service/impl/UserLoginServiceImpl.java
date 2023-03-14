package com.onezed.learn.security.service.impl;

import com.onezed.learn.security.common.ResponseResult;
import com.onezed.learn.security.mapper.UserLoginMapper;
import com.onezed.learn.security.manager.UserAuthentication;
import com.onezed.learn.security.model.po.UserPO;
import com.onezed.learn.security.model.vo.LoginVO;
import com.onezed.learn.security.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p/>
 * Describe
 * <p/>
 *
 * @author lianwenjie
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public ResponseResult<String> login(LoginVO loginVO) {
        //验证获取用户信息
        UserPO user = userLoginMapper.selectUser(loginVO);
        if (user == null) {
            throw new AuthenticationException("登录失败,用户密码错误") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
        //封装成Authentication,存入SecurityContext上下文中,用于后续的过滤器链验证
        String roles = user.getUserRole();
        List<String> roleList = Arrays.asList(roles.split(","));
        List<GrantedAuthority> grantList = new ArrayList<>();
        roleList.forEach(role -> {
            GrantedAuthority grant = new SimpleGrantedAuthority(role);
            grantList.add(grant);
        });
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new UserAuthentication(user.getUsername(),user.getUserPassword(),grantList);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        return ResponseResult.success("登录成功");
    }

    @Override
    public UserPO jwtLogin(String token) {
        UserPO user = userLoginMapper.selectUserOnToken(token);
        return user;
    }

}
