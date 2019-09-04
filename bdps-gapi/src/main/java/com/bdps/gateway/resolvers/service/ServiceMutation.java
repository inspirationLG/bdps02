package com.bdps.gateway.resolvers.service;

import com.bdps.service.ServiceProto;
import com.bdps.gateway.clients.ServiceClient;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ServiceMutation implements GraphQLMutationResolver {
    @Autowired
    private final ServiceClient serviceClient;

    public ListenableFuture<ServiceProto.Service> addService(ServiceProto.AddServiceRequest request) {
        return serviceClient.addService(request);
    }

    public ListenableFuture<ServiceProto.Service> updateService(ServiceProto.UpdateServiceRequest request) {
        return serviceClient.updateService(request);
    }

    public ListenableFuture<ServiceProto.Service> deleteService(ServiceProto.DeleteServiceRequest request) {
        return serviceClient.deleteService(request);
    }
}
