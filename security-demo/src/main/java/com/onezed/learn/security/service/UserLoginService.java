package com.onezed.learn.security.service;

import com.onezed.learn.security.common.ResponseResult;
import com.onezed.learn.security.model.po.UserPO;
import com.onezed.learn.security.model.vo.LoginVO;

/**
 * <p/>
 * Describe
 * <p/>
 *
 * @author lianwenjie
 */
public interface UserLoginService {

    ResponseResult<String> login(LoginVO loginVO);

    UserPO jwtLogin(String token);
}
