package com.bdps.gateway.resolvers.special_service_fund;

import com.bdps.gateway.clients.SpecialServiceFundClient;
import com.bdps.special_service_fund.SpecialServiceFundProto;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Hchien Ying
 * @date:2019/8/31
 * @description:
 */
@AllArgsConstructor
@Component
public class SpecialServiceFundMutation implements GraphQLMutationResolver {
    @Autowired
    private final SpecialServiceFundClient specialServiceFundClient;

    public ListenableFuture<SpecialServiceFundProto.SpecialServiceFund> addSpecialServiceFund(SpecialServiceFundProto.AddSpecialServiceFundRequest request) {
        return specialServiceFundClient.addSpecialServiceFund(request);
    }
    public ListenableFuture<SpecialServiceFundProto.SpecialServiceFund> updateSpecialServiceFund(SpecialServiceFundProto.UpdateSpecialServiceFundRequest request) {
        return specialServiceFundClient.updateSpecialServiceFund(request);
    }
    public ListenableFuture<SpecialServiceFundProto.SpecialServiceFund> deleteSpecialServiceFund(SpecialServiceFundProto.DeleteSpecialServiceFundRequest request) {
        return specialServiceFundClient.deleteSpecialServiceFund(request.getSpecialServiceFundId());
    }
}
