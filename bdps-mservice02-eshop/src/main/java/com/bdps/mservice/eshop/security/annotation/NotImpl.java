package com.bdps.mservice.eshop.security.annotation;

/**
 * @Author:Hchien Ying
 * @date:2019/8/20
 * @description:
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 未实现rpc用此a
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface NotImpl {
}
