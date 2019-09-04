package com.bdps.gateway.resolvers.service;

import com.bdps.service.ServiceProto;
import com.bdps.service_type.ServiceTypeProto;
import com.bdps.gateway.clients.ServiceTypeClient;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.util.concurrent.ListenableFuture;

@Component
public class ServiceResolver implements GraphQLResolver<ServiceProto.Service> {
    @Autowired
    private ServiceTypeClient serviceTypeClient;

    public ListenableFuture <ServiceTypeProto.ServiceType> serviceType(ServiceProto.Service service) {
        return serviceTypeClient.getServiceType(service.getServiceId());
    }
}
