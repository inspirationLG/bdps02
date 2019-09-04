package com.bdps.gateway.clients;

import com.bdps.organization.OrganizationProto;
import com.bdps.organization.OrganizationServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @Author:Hchien Ying
 * @date:2019/8/15
 * @description:
 */
@Service
public class OrganizationClient {

    @GrpcClient("user-org-info-grpc-server")
    private OrganizationServiceGrpc.OrganizationServiceFutureStub organizationServiceFutureStub;

    public ListenableFuture<OrganizationProto.Organization> addOrganization(OrganizationProto.AddOrganizationRequest request){
        return organizationServiceFutureStub.addOrganization(request);
    }

    public ListenableFuture<OrganizationProto.Organizations> listOrganizations(OrganizationProto.ListOrganizationRequest request){
        return organizationServiceFutureStub.listOrganization(request);
    }


    public ListenableFuture<OrganizationProto.Organization> updateOrganization(OrganizationProto.UpdateOrganizationRequest request){
        return organizationServiceFutureStub.updateOrganization(request);
    }

    public ListenableFuture<OrganizationProto.Organization> deleteOrganization(OrganizationProto.DeleteOrganizationRequest request){
        return organizationServiceFutureStub.deleteOrganization(request);
    }

    public ListenableFuture<OrganizationProto.Organization> getOrganization(Integer id) {
        return organizationServiceFutureStub.getOrganization(OrganizationProto.GetOrganizationRequest.newBuilder().setOrganizationId(id).build());
    }

}
