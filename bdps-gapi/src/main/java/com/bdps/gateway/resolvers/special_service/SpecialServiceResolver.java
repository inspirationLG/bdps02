package com.bdps.gateway.resolvers.special_service;

import com.bdps.gateway.clients.OrganizationClient;
import com.bdps.organization.OrganizationProto;
import com.bdps.special_service.SpecialServiceProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Hchien Ying
 * @date:2019/9/1
 * @description:
 */
@Component
public class SpecialServiceResolver implements GraphQLResolver<SpecialServiceProto.SpecialService> {
    @Autowired
    private OrganizationClient organizationClient;
    public ListenableFuture<OrganizationProto.Organization> organization(SpecialServiceProto.SpecialService specialService){
        return organizationClient.getOrganization(specialService.getOrganizationId());
    }
}
