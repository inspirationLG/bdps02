package com.bdps.gateway.clients;


import com.bdps.service_type.ServiceTypeProto;
import com.bdps.service_type.ServiceTypeServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class ServiceTypeClient {

    @GrpcClient("eshop-grpc-server")
    private ServiceTypeServiceGrpc.ServiceTypeServiceFutureStub serviceTypeServiceFutureStub;

    public ListenableFuture<ServiceTypeProto.ServiceType> addServiceType(ServiceTypeProto.AddServiceTypeRequest request) {
        return serviceTypeServiceFutureStub.addServiceType(request);
    }

    public ListenableFuture<ServiceTypeProto.ServiceType> updateServiceType(ServiceTypeProto.UpdateServiceTypeRequest request) {
        return serviceTypeServiceFutureStub.updateServiceType(request);
    }

    public ListenableFuture<ServiceTypeProto.ServiceType> deleteServiceType(ServiceTypeProto.DeleteServiceTypeRequest request) {
        return serviceTypeServiceFutureStub.deleteServiceType(request);
    }

    public ListenableFuture<ServiceTypeProto.ServiceTypes> listServiceType(ServiceTypeProto.ListServiceTypeRequest request){
        return serviceTypeServiceFutureStub.listServiceType(request);
    }

    public ListenableFuture<ServiceTypeProto.ServiceType> getServiceType(Integer serviceTypeId) {
        return serviceTypeServiceFutureStub.getServiceType(ServiceTypeProto.GetServiceTypeRequest.newBuilder().setServiceTypeId(serviceTypeId).build());
    }
}
