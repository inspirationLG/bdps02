package com.bdps.mservice.eshop.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author:Hchien Ying
 * @date:2019/8/20
 * @description:
 */
public class UserFromGatewayAuthenticationToken extends AbstractAuthenticationToken {
    private final String userId;
    public UserFromGatewayAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String userId) {
        super(authorities);
        this.userId = userId;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }
}
