package com.bdps.gateway.resolvers.service;

import com.bdps.service.ServiceProto;
import com.bdps.gateway.clients.ServiceClient;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ServiceQuery implements GraphQLQueryResolver {
    private final ServiceClient serviceClient;

    public  ListenableFuture<ServiceProto.Services> listService(ServiceProto.ListServiceRequest request){
        return serviceClient.listService(request);
    }

    public ListenableFuture<ServiceProto.Service> getService(ServiceProto.GetServiceRequest request){
        return serviceClient.getService(request.getServiceId());
    }
}
