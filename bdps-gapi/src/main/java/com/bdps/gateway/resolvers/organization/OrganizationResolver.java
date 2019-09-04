package com.bdps.gateway.resolvers.organization;

import com.bdps.gateway.clients.OrganizationClient;
import com.bdps.gateway.clients.RegionClient;
import com.bdps.organization.OrganizationProto;
import com.bdps.region.RegionProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Hchien Ying
 * @date:2019/8/26
 * @description:
 */
@Component
public class OrganizationResolver implements GraphQLResolver<OrganizationProto.Organization> {

    @Autowired
    private OrganizationClient organizationClient;

    @Autowired
    private RegionClient regionClient;
    public ListenableFuture<OrganizationProto.Organization> parentOrganization(OrganizationProto.Organization organization) {
        return organizationClient.getOrganization(organization.getParentOrganizationId());
    }

    public ListenableFuture<RegionProto.Region> region(OrganizationProto.Organization organization) {
        return regionClient.getRegion(organization.getRegionId());
    }

}
