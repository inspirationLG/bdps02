package com.bdps.gateway.clients;

import com.bdps.region.RegionProto;
import com.bdps.region.RegionServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author zcz
 * @CreateTime 2019/8/14 20:19
 */
@Service
public class RegionClient {
    @GrpcClient("user-org-info-grpc-server")
    private RegionServiceGrpc.RegionServiceFutureStub regionServiceFutureStub;

    public ListenableFuture<RegionProto.Region> addRegion(RegionProto.AddRegionRequest request) {
        return regionServiceFutureStub.addRegion(request);
    }

    public ListenableFuture<RegionProto.Region> updateRegion(RegionProto.UpdateRegionRequest request) {
        return regionServiceFutureStub.updateRegion(request);
    }

    public ListenableFuture<RegionProto.Regions> listRegions(RegionProto.ListRegionRequest request) {
        return regionServiceFutureStub.listRegion(request);
    }

    public ListenableFuture<RegionProto.Region> deleteRegion(RegionProto.DeleteRegionRequest request) {
        return regionServiceFutureStub.deleteRegion(request);
    }

    public ListenableFuture<RegionProto.Region> getRegion(Integer id) {
        return regionServiceFutureStub.getRegion(RegionProto.GetRegionRequest.newBuilder().setId(id).build());
    }
}
