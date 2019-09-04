package com.bdps.gateway.clients;

import com.bdps.special_service.SpecialServiceProto;
import com.bdps.special_service.SpecialServiceServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SpecialServiceClient {
    @GrpcClient("eshop-grpc-server")
    private SpecialServiceServiceGrpc.SpecialServiceServiceFutureStub specialServiceServiceFutureStub;
    public ListenableFuture<SpecialServiceProto.SpecialService> addSpecialService(SpecialServiceProto.AddSpecialServiceRequest request) {
        return specialServiceServiceFutureStub.addSpecialService(request);
    }
    public ListenableFuture<SpecialServiceProto.SpecialService> updateSpecialService(SpecialServiceProto.UpdateSpecialServiceRequest request) {
        return specialServiceServiceFutureStub.updateSpecialService(request);
    }
    public ListenableFuture<SpecialServiceProto.SpecialServices> ListSpecialServices(SpecialServiceProto.ListSpecialServiceRequest request) {
        return specialServiceServiceFutureStub.listSpecialService(request);
    }
    public ListenableFuture<SpecialServiceProto.SpecialService> getSpecialService(Integer specialServiceId) {
        return specialServiceServiceFutureStub.getSpecialService(SpecialServiceProto.GetSpecialServiceRequest.newBuilder().setSpecialServiceId(specialServiceId).build());
    }
    public ListenableFuture<SpecialServiceProto.SpecialService> deleteSpecialService(Integer specialServiceId) {
        return specialServiceServiceFutureStub.deleteSpecialService(SpecialServiceProto.DeleteSpecialServiceRequest.newBuilder().setSpecialServiceId(specialServiceId).build());
    }

}
