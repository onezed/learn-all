package com.onezed.learn.security.config;

import com.onezed.learn.security.filter.JwtFilter;
import com.onezed.learn.security.manager.MyAccessDecisionManager;
import com.onezed.learn.security.manager.MyAccessDeniedHandler;
import com.onezed.learn.security.manager.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 * description
 * </p>
 *
 * @author onezed
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private MyAccessDecisionManager accessDecisionManager;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用csrf
                .csrf().disable()
                // 取消session会话存储
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //放通自定义登录接口
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/v1.0/login").anonymous()
                        .antMatchers("/v1.0/hello").hasRole("demo")
                        .anyRequest().authenticated()
                );

        //配置自定义权限认证
        http.authorizeRequests()
                .accessDecisionManager(accessDecisionManager);

        //配置自定义异常处理
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        //配置自定义过滤器
        http.addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        //配置自定义authenticationProvider
        http.authenticationProvider(authenticationProvider);

    }
}
