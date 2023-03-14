package com.onezed.learn.security.manager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * <p/>
 * Describe
 * <p/>
 *
 * @author lianwenjie
 */
public class UserAuthentication implements Authentication {

    private String name;

    private String password;

    private List<GrantedAuthority> grantedList;

    private boolean authenticated = false;

    public UserAuthentication (String name, String password, List<GrantedAuthority> grantedList) {
        this.name = name;
        this.password = password;
        this.grantedList = grantedList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedList;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return name;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return name;
    }
}
