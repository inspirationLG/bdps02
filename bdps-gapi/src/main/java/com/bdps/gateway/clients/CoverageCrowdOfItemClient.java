package com.bdps.gateway.clients;

import com.bdps.coverage_crowd_of_item.CoverageCrowdOfItemProto;
import com.bdps.coverage_crowd_of_item.CoverageCrowdOfItemServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author zcz
 * @CreateTime 2019/8/29 13:37
 */
@Service
public class CoverageCrowdOfItemClient {
    @GrpcClient("user-org-info-grpc-server")
    private CoverageCrowdOfItemServiceGrpc.CoverageCrowdOfItemServiceFutureStub coverageCrowdOfItemServiceFutureStub;

    public ListenableFuture<CoverageCrowdOfItemProto.CoverageCrowdOfItem> addCoverageCrowdOfItem(CoverageCrowdOfItemProto.AddCoverageCrowdOfItemRequest request) {
        return coverageCrowdOfItemServiceFutureStub.addCoverageCrowdOfItem(request);
    }

    public ListenableFuture<CoverageCrowdOfItemProto.CoverageCrowdOfItem> updateCoverageCrowdOfItem(CoverageCrowdOfItemProto.UpdateCoverageCrowdOfItemRequest request) {
        return coverageCrowdOfItemServiceFutureStub.updateCoverageCrowdOfItem(request);
    }

    public ListenableFuture<CoverageCrowdOfItemProto.CoverageCrowdOfItems> listCoverageCrowdOfItems(CoverageCrowdOfItemProto.ListCoverageCrowdOfItemRequest request) {
        return coverageCrowdOfItemServiceFutureStub.listCoverageCrowdOfItem(request);
    }

    public ListenableFuture<CoverageCrowdOfItemProto.CoverageCrowdOfItem> deleteCoverageCrowdOfItem(CoverageCrowdOfItemProto.DeleteCoverageCrowdOfItemRequest request) {
        return coverageCrowdOfItemServiceFutureStub.deleteCoverageCrowdOfItem(request);
    }

    public ListenableFuture<CoverageCrowdOfItemProto.CoverageCrowdOfItem> getCoverageCrowdOfItem(Integer id) {
        return coverageCrowdOfItemServiceFutureStub.getCoverageCrowdOfItem(CoverageCrowdOfItemProto.GetCoverageCrowdOfItemRequest.newBuilder().setCoverageCrowdOfItemId(id).build());
    }
}
