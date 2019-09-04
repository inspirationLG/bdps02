package com.bdps.gateway.configure;

import com.bdps.gateway.clients.interceptor.LogGrpcInterceptor;
import io.envoyproxy.pgv.ReflectiveValidatorIndex;
import io.envoyproxy.pgv.grpc.ValidatingClientInterceptor;
import net.devh.boot.grpc.client.interceptor.GlobalClientInterceptorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.LOWEST_PRECEDENCE)
@Configuration
public class GlobalClientInterceptorConfiguration {

    @Bean
    public GlobalClientInterceptorConfigurer globalInterceptorConfigurerAdapter() {
        return registry -> registry
                .addClientInterceptors(new LogGrpcInterceptor())
                .addClientInterceptors(new ValidatingClientInterceptor(new ReflectiveValidatorIndex()));
    }

}
