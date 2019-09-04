package com.bdps.gateway.configure;

import com.bdps.gateway.scalar.CustomScalars;
import graphql.schema.GraphQLScalarType;
import graphql.servlet.core.ApolloScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScalarConfiguration {
    @Bean
    public GraphQLScalarType uploadScalar(){
        return ApolloScalars.Upload;
    }

    @Bean
    public GraphQLScalarType dateTimeScalar(){
        return CustomScalars.DateTime;
    }

}
