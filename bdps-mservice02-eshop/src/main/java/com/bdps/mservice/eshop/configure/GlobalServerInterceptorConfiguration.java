//package com.bdps.mservice.eshop.configure;
//
//import io.envoyproxy.pgv.ReflectiveValidatorIndex;
//import io.envoyproxy.pgv.grpc.ValidatingServerInterceptor;
//import io.grpc.util.TransmitStatusRuntimeExceptionInterceptor;
//import net.devh.boot.grpc.server.interceptor.GlobalServerInterceptorConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GlobalServerInterceptorConfiguration {
//
//    @Bean
//    public GlobalServerInterceptorConfigurer globalInterceptorConfigurerAdapter() {
//        return registry -> registry
//			.addServerInterceptors(new ValidatingServerInterceptor(new ReflectiveValidatorIndex()))
//			.addServerInterceptors(TransmitStatusRuntimeExceptionInterceptor.instance());
//    }
//
//}
