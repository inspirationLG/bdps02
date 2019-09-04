package com.bdps.mservice.eshop.security.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author:Hchien Ying
 * @date:2019/8/20
 * @description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface NoSecure {
}
