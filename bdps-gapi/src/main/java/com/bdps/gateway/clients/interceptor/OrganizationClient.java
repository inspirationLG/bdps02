/*package com.bdps.gateway.clients;

import com.bdps.organization.OrganizationProto;
import com.bdps.organization.OrganizationServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

*//**
 * @Author:Hchien Ying
 * @date:2019/8/15
 * @description:
 *//*
@Service
public class OrganizationClient {

    @GrpcClient("user-org-info-grpc-server")
    private OrganizationServiceGrpc.OrganizationServiceFutureStub organizationServiceFutureStub;

    public ListenableFuture<OrganizationProto.Organization> addOrganization(OrganizationProto.AddOrganizationRequest request) {
        return organizationServiceFutureStub.addOrganization(request);
    }

    public ListenableFuture<OrganizationProto.Organization> getOrganization(OrganizationProto.GetOrganizationRequest request) {
        organizationServiceFutureStub.
        return organizationServiceFutureStub.getOrganization(request);

    }

}*/
