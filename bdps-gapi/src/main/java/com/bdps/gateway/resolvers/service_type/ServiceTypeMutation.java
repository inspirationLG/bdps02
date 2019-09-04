package com.bdps.gateway.resolvers.service_type;


import com.bdps.service_type.ServiceTypeProto;
import com.bdps.gateway.clients.ServiceTypeClient;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class ServiceTypeMutation implements GraphQLMutationResolver {
    @Autowired
    private final ServiceTypeClient serviceTypeClient;

    public ListenableFuture<ServiceTypeProto.ServiceType> addServiceType(ServiceTypeProto.AddServiceTypeRequest request) {
        return serviceTypeClient.addServiceType(request);
    }

    public ListenableFuture<ServiceTypeProto.ServiceType> updateServiceType(ServiceTypeProto.UpdateServiceTypeRequest request) {
        return serviceTypeClient.updateServiceType(request);
    }

    public ListenableFuture<ServiceTypeProto.ServiceType> deleteServiceType(ServiceTypeProto.DeleteServiceTypeRequest request) {
        return serviceTypeClient.deleteServiceType(request);
    }

}
