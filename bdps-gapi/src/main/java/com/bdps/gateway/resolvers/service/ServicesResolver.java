package com.bdps.gateway.resolvers.service;

import com.bdps.service.ServiceProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ServicesResolver implements GraphQLResolver<ServiceProto.Services> {
    public List<ServiceProto.Service> nodes(ServiceProto.Services services) {
        return services.getNodesList();
    }
}