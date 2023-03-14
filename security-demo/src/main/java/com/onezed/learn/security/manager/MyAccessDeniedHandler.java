package com.onezed.learn.security.manager;

import com.alibaba.fastjson2.JSONObject;
import com.onezed.learn.security.common.ResponseEnum;
import com.onezed.learn.security.common.ResponseResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        String message = e.getMessage();
        response.setContentType("application/json;charset=utf8");
        response.setStatus(ResponseEnum.UNAUTHORIZED.code());
        PrintWriter writer = response.getWriter();
        ResponseResult<String> result = ResponseResult.failed(ResponseEnum.UNAUTHORIZED.code(), message);
        String json = JSONObject.toJSONString(result);
        writer.println(json);
    }
}
