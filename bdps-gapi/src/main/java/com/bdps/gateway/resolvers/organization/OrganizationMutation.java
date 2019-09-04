package com.bdps.gateway.resolvers.organization;

import com.bdps.gateway.clients.OrganizationClient;
import com.bdps.organization.OrganizationProto;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
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
public class OrganizationMutation implements GraphQLMutationResolver {
    private final OrganizationClient organizationClient;

    public ListenableFuture<OrganizationProto.Organization> addOrganization(OrganizationProto.AddOrganizationRequest request) {
        return organizationClient.addOrganization(request);
    }

    public ListenableFuture<OrganizationProto.Organization> updateOrganization(OrganizationProto.UpdateOrganizationRequest request) {
        return organizationClient.updateOrganization(request);
    }


    public ListenableFuture<OrganizationProto.Organization> deleteOrganization(OrganizationProto.DeleteOrganizationRequest request) {
        return organizationClient.deleteOrganization(request);
    }
}

