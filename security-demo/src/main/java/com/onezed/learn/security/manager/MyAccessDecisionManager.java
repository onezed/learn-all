package com.onezed.learn.security.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * <p/>
 * Describe
 * <p/>
 *
 * @author lianwenjie
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    private final Log logger = LogFactory.getLog(MyAccessDecisionManager.class);

    /**
     * 如果使用FilterSecurityInterceptor过滤器进行权限验证,
     * object 实质为FilterSecurityInterceptor将(request,response,filterChain)封装成的FilterInvocation
     *
     * @param authentication SecurityContext上下文中的用户信息
     * @param object FilterInvocation
     * @param configAttributes 相应路径或接口配置权限信息,例如在 /demo 这个访问路径配置demo角色才能访问,
     *                         则ConfigAttribute存的是(ROLE_demo)这些角色限制
     * @throws AccessDeniedException 验证不通过抛出的异常
     * @throws InsufficientAuthenticationException 如果由于身份验证未提供足够的信任级别而拒绝访问
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        logger.info("自定义权限认证manager开始验证");

        FilterInvocation filterInvocation = (FilterInvocation) object;
        String url = filterInvocation.getRequestUrl();
        logger.info(url);
        if (url.contains("/login")) {
            return;
        }
        logger.info("flag=======");

        boolean flag = false;
        Collection<? extends GrantedAuthority> grants = authentication.getAuthorities();
        for (GrantedAuthority g : grants) {
            if (g != null) {
                String var1 = g.getAuthority();
                if ("demo".equals(var1)) {
                    flag = true;
                    break;
                }
            }
        }

        if (!flag) {
            throw new AccessDeniedException("认证失败,权限不足");
        }
        logger.info("认证成功");


    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
