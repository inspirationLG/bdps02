package com.bdps.gateway.resolvers.organization;

import com.bdps.gateway.clients.OrganizationClient;
import com.bdps.organization.OrganizationProto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author:Hchien Ying
 * @date:2019/8/21
 * @description:
 */
@AllArgsConstructor
@Component
public class OrganizationQuery implements GraphQLQueryResolver {
    private final OrganizationClient organizationClient;

    public ListenableFuture<OrganizationProto.Organizations> listOrganization(OrganizationProto.ListOrganizationRequest request) {
        return organizationClient.listOrganizations(request);
    }

    public ListenableFuture<OrganizationProto.Organization> getOrganization(OrganizationProto.GetOrganizationRequest request) {
        return organizationClient.getOrganization(request.getOrganizationId());
    }
}
