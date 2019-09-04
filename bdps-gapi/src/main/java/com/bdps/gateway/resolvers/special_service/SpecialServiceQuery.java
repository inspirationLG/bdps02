package com.bdps.gateway.resolvers.special_service;

import com.bdps.gateway.clients.SpecialServiceClient;
import com.bdps.gateway.clients.SpecialServiceFundClient;
import com.bdps.special_service.SpecialServiceProto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Hchien Ying
 * @date:2019/9/1
 * @description:
 */
@AllArgsConstructor
@Component
public class SpecialServiceQuery implements GraphQLQueryResolver {
    @Autowired
    private final SpecialServiceClient specialServiceClient;
    public ListenableFuture<SpecialServiceProto.SpecialServices> listSpecialService(SpecialServiceProto.ListSpecialServiceRequest request) {
        return specialServiceClient.ListSpecialServices(request);
    }
    public ListenableFuture<SpecialServiceProto.SpecialService> getSpecialService(SpecialServiceProto.GetSpecialServiceRequest request) {
        return specialServiceClient.getSpecialService(request.getSpecialServiceId());
    }

}
