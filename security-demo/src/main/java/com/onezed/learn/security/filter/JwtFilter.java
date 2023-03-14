package com.onezed.learn.security.filter;

import com.onezed.learn.security.manager.UserAuthentication;
import com.onezed.learn.security.model.po.UserPO;
import com.onezed.learn.security.service.UserLoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(JwtFilter.class);

    @Autowired
    private UserLoginService userLoginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        UserPO user = userLoginService.jwtLogin(token);
        if (user == null) {
            filterChain.doFilter(request,response);
            return;
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
        logger.info("封装好Authentication向后传递");
        filterChain.doFilter(request,response);
    }
}
