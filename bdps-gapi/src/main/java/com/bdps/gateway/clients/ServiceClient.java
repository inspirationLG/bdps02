package com.bdps.gateway.clients;


import com.bdps.service.ServiceProto;
import com.bdps.service.ServiceServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class ServiceClient {

    @GrpcClient("eshop-grpc-server")
    private ServiceServiceGrpc.ServiceServiceFutureStub serviceServiceFutureStub;

    public ListenableFuture<ServiceProto.Service> addService(ServiceProto.AddServiceRequest request) {
        return serviceServiceFutureStub.addService(request);
    }

    public ListenableFuture<ServiceProto.Service> updateService(ServiceProto.UpdateServiceRequest request) {
        return serviceServiceFutureStub.updateService(request);
    }

    public ListenableFuture<ServiceProto.Service> deleteService(ServiceProto.DeleteServiceRequest request) {
        return serviceServiceFutureStub.deleteService(request);
    }

    public ListenableFuture<ServiceProto.Services> listService(ServiceProto.ListServiceRequest request) {
        return serviceServiceFutureStub.listService(request);
    }

    public ListenableFuture<ServiceProto.Service> getService(Integer serviceId) {
        return serviceServiceFutureStub.getService(ServiceProto.GetServiceRequest.newBuilder().setServiceId(serviceId).build());
    }
}
