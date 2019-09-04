package com.bdps.gateway.clients;


import com.bdps.coverage_crowd.CoverageCrowdServiceGrpc.CoverageCrowdServiceFutureStub;
import com.bdps.coverage_crowd.CoverageCrowdProto;
import com.bdps.coverage_crowd.CoverageCrowdServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author zcz
 * @CreateTime 2019/8/28 9:19
 */
@Service
public class CoverageCrowdClient {
    @GrpcClient("user-org-info-grpc-server")
    private CoverageCrowdServiceGrpc.CoverageCrowdServiceFutureStub coverageCrowdServiceFutureStub;
    public ListenableFuture<CoverageCrowdProto.CoverageCrowd> addCoverageCrowd(CoverageCrowdProto.AddCoverageCrowdRequest request){
        return coverageCrowdServiceFutureStub.addCoverageCrowd(request);
    }
    public ListenableFuture<CoverageCrowdProto.CoverageCrowd>updateCoverageCrowd(CoverageCrowdProto.UpdateCoverageCrowdRequest request){
        return coverageCrowdServiceFutureStub.updateCoverageCrowd(request);
    }
    public ListenableFuture<CoverageCrowdProto.CoverageCrowds>listCoverageCrowds(CoverageCrowdProto.ListCoverageCrowdRequest request){
        return coverageCrowdServiceFutureStub.listCoverageCrowd(request);
    }
    public ListenableFuture<CoverageCrowdProto.CoverageCrowd> getCoverageCrowd(Integer coverageCrowdId){
        return coverageCrowdServiceFutureStub.getCoverageCrowd(CoverageCrowdProto.GetCoverageCrowdRequest.newBuilder().setCoverageCrowdId(coverageCrowdId).build());
    }

    public ListenableFuture<CoverageCrowdProto.CoverageCrowd> deleteCoverageCrowd(Integer coverageCrowdId){
        return coverageCrowdServiceFutureStub.deleteCoverageCrowd(CoverageCrowdProto.DeleteCoverageCrowdRequest.newBuilder().setCoverageCrowdId(coverageCrowdId).build());
    }
}
