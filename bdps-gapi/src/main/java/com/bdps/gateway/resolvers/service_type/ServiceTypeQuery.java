package com.bdps.gateway.resolvers.service_type;

import com.bdps.service_type.ServiceTypeProto;
import com.bdps.gateway.clients.ServiceTypeClient;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class ServiceTypeQuery implements GraphQLQueryResolver {
    private final ServiceTypeClient serviceTypeClient;

    public ListenableFuture<ServiceTypeProto.ServiceTypes> listServiceType(ServiceTypeProto.ListServiceTypeRequest request) {
        return serviceTypeClient.listServiceType(request);
    }

    public ListenableFuture<ServiceTypeProto.ServiceType> getServiceType(ServiceTypeProto.GetServiceTypeRequest request) {
        return serviceTypeClient.getServiceType(request.getServiceTypeId());
    }
}
