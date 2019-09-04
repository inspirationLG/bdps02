package com.bdps.gateway.resolvers.special_service;

import com.bdps.gateway.clients.SpecialServiceClient;
import com.bdps.special_service.SpecialServiceProto;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
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
public class SpecialServiceMutation implements GraphQLMutationResolver {
    @Autowired
    private final SpecialServiceClient specialServiceClient;

    public ListenableFuture<SpecialServiceProto.SpecialService> addSpecialService(SpecialServiceProto.AddSpecialServiceRequest request) {
        return specialServiceClient.addSpecialService(request);
    }
    public ListenableFuture<SpecialServiceProto.SpecialService> updateSpecialService(SpecialServiceProto.UpdateSpecialServiceRequest request) {
        return specialServiceClient.updateSpecialService(request);
    }
    public ListenableFuture<SpecialServiceProto.SpecialService> deleteSpecialService(SpecialServiceProto.DeleteSpecialServiceRequest request) {
        return specialServiceClient.deleteSpecialService(request.getSpecialServiceId());
    }
}
