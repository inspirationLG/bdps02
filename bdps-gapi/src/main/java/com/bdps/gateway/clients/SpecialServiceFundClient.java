package com.bdps.gateway.clients;

import com.bdps.special_service_fund.SpecialServiceFundProto;
import com.bdps.special_service_fund.SpecialServiceFundServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @Author:Hchien Ying
 * @date:2019/8/31
 * @description:
 */
@Service
@Slf4j
public class SpecialServiceFundClient {
    @GrpcClient("eshop-grpc-server")
    private SpecialServiceFundServiceGrpc.SpecialServiceFundServiceFutureStub specialServiceFundServiceFutureStub;
    public ListenableFuture<SpecialServiceFundProto.SpecialServiceFund> addSpecialServiceFund(SpecialServiceFundProto.AddSpecialServiceFundRequest request) {
        return specialServiceFundServiceFutureStub.addSpecialServiceFund(request);
    }
    public ListenableFuture<SpecialServiceFundProto.SpecialServiceFund> updateSpecialServiceFund(SpecialServiceFundProto.UpdateSpecialServiceFundRequest request) {
        return specialServiceFundServiceFutureStub.updateSpecialServiceFund(request);
    }
    public ListenableFuture<SpecialServiceFundProto.SpecialServiceFunds> ListSpecialServiceFunds(SpecialServiceFundProto.ListSpecialServiceFundRequest request) {
        return specialServiceFundServiceFutureStub.listSpecialServiceFund(request);
    }
    public ListenableFuture<SpecialServiceFundProto.SpecialServiceFund> getSpecialServiceFund(Integer specialServiceFundId) {
        return specialServiceFundServiceFutureStub.getSpecialServiceFund(SpecialServiceFundProto.GetSpecialServiceFundRequest.newBuilder().setSpecialServiceFundId(specialServiceFundId).build());
    }
    public ListenableFuture<SpecialServiceFundProto.SpecialServiceFund> deleteSpecialServiceFund(Integer specialServiceFundId) {
        return specialServiceFundServiceFutureStub.deleteSpecialServiceFund(SpecialServiceFundProto.DeleteSpecialServiceFundRequest.newBuilder().setSpecialServiceFundId(specialServiceFundId).build());
    }
}
