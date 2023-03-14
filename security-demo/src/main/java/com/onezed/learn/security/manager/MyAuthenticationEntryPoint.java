package com.onezed.learn.security.manager;

import com.alibaba.fastjson2.JSONObject;
import com.onezed.learn.security.common.ResponseEnum;
import com.onezed.learn.security.common.ResponseResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p/>
 * Describe
 * <p/>
 *
 * @author lianwenjie
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String message = e.getMessage();
        response.setContentType("application/json;charset=utf8");
        response.setStatus(ResponseEnum.FORBIDDEN.code());
        PrintWriter writer = response.getWriter();
        ResponseResult<String> result = ResponseResult.failed(ResponseEnum.FORBIDDEN.code(), message);
        String json = JSONObject.toJSONString(result);
        writer.println(json);
    }
}
