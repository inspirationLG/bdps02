package com.bdps.gateway.resolvers.special_service;

import com.bdps.special_service.SpecialServiceProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Hchien Ying
 * @date:2019/9/1
 * @description:
 */
@Component

public class SpecialServicesResolver implements GraphQLResolver<SpecialServiceProto.SpecialServices> {
    public List<SpecialServiceProto.SpecialService> nodes(SpecialServiceProto.SpecialServices specialServices) {
        return specialServices.getNodesList();
    }
}
