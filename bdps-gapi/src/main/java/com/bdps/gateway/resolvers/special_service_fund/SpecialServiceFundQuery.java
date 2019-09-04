package com.bdps.gateway.resolvers.special_service_fund;

import com.bdps.gateway.clients.SpecialServiceFundClient;
import com.bdps.special_service_fund.SpecialServiceFundProto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
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
public class SpecialServiceFundQuery implements GraphQLQueryResolver {
    @Autowired
    private final SpecialServiceFundClient specialServiceFundClient;
    public ListenableFuture<SpecialServiceFundProto.SpecialServiceFunds> listSpecialServiceFund(SpecialServiceFundProto.ListSpecialServiceFundRequest request) {
        return specialServiceFundClient.ListSpecialServiceFunds(request);
    }
    public ListenableFuture<SpecialServiceFundProto.SpecialServiceFund> getSpecialServiceFund(SpecialServiceFundProto.GetSpecialServiceFundRequest request) {
        return specialServiceFundClient.getSpecialServiceFund(request.getSpecialServiceFundId());
    }
}
