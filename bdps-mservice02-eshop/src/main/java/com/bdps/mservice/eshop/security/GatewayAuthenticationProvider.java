package com.bdps.mservice.eshop.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @Author:Hchien Ying
 * @date:2019/8/20
 * @description:
 */
public class GatewayAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO 远程获取用户的权限
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserFromGatewayAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
