package com.bdps.gateway.clients;

import com.bdps.special_service_item.SpecialServiceItemProto;
import com.bdps.special_service_item.SpecialServiceItemServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author zcz
 * @CreateTime 2019/8/29 15:39
 */
@Service
public class SpecialServiceItemClient {
    @GrpcClient("eshop-grpc-server")
    private SpecialServiceItemServiceGrpc.SpecialServiceItemServiceFutureStub specialServiceItemServiceFutureStub;

    public ListenableFuture<SpecialServiceItemProto.SpecialServiceItem> addSpecialServiceItem(SpecialServiceItemProto.AddSpecialServiceItemRequest request) {
        return specialServiceItemServiceFutureStub.addSpecialServiceItem(request);
    }

    public ListenableFuture<SpecialServiceItemProto.SpecialServiceItem> updateSpecialServiceItem(SpecialServiceItemProto.UpdateSpecialServiceItemRequest request) {
        return specialServiceItemServiceFutureStub.updateSpecialServiceItem(request);
    }

    public ListenableFuture<SpecialServiceItemProto.SpecialServiceItems> listSpecialServiceItems(SpecialServiceItemProto.ListSpecialServiceItemRequest request) {
        return specialServiceItemServiceFutureStub.listSpecialServiceItem(request);
    }

    public ListenableFuture<SpecialServiceItemProto.SpecialServiceItem> deleteSpecialServiceItem(SpecialServiceItemProto.DeleteSpecialServiceItemRequest request) {
        return specialServiceItemServiceFutureStub.deleteSpecialServiceItem(request);
    }

    public ListenableFuture<SpecialServiceItemProto.SpecialServiceItem> getSpecialServiceItem(Integer id) {
        return specialServiceItemServiceFutureStub.getSpecialServiceItem(SpecialServiceItemProto.GetSpecialServiceItemRequest.newBuilder().setSpecialServiceItemId(id).build());
    }
}