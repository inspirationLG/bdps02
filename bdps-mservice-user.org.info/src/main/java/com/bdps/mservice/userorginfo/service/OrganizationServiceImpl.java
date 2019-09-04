package com.bdps.mservice.userorginfo.service;

import com.bdps.mservice.userorginfo.model.BdpsOrganization;
import com.bdps.mservice.userorginfo.model.BdpsRegion;
import com.bdps.mservice.userorginfo.repository.OrganizationRepository;
import com.bdps.mservice.userorginfo.util.CopyUtils;
import com.bdps.mservice.userorginfo.util.EntityUtil;
import com.bdps.organization.OrganizationProto;
import com.bdps.organization.OrganizationServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.badata.protobuf.converter.Configuration;
import net.badata.protobuf.converter.Converter;
import net.badata.protobuf.converter.FieldsIgnore;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@GrpcService
public class OrganizationServiceImpl extends OrganizationServiceGrpc.OrganizationServiceImplBase {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Transactional
    @Override
    public void addOrganization(OrganizationProto.AddOrganizationRequest request, StreamObserver<OrganizationProto.Organization> responseObserver) {
        int parentOrganizationId = request.getParentOrganizationId();
        if (parentOrganizationId == 0 || organizationRepository.existsById(parentOrganizationId)) {
            Configuration configuration = Configuration.builder()
                    .addIgnoredFields(new FieldsIgnore().add(BdpsOrganization.class, "organizationId"))
                    .build();
            BdpsOrganization bdpsOrganization = Converter.create(configuration).toDomain(BdpsOrganization.class, request);
            EntityUtil.fill(bdpsOrganization);
            BdpsOrganization newbdpsOrganization = organizationRepository.save(bdpsOrganization);
            responseObserver.onNext(modelToRpc(newbdpsOrganization));
            responseObserver.onCompleted();
        }
        throw Status.NOT_FOUND.withDescription("organizationId不存在！").asRuntimeException();

    }

    @Override
    public void listOrganization(OrganizationProto.ListOrganizationRequest request, StreamObserver<OrganizationProto.Organizations> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsOrganization> page = organizationRepository.findAll(pageable);
        responseObserver.onNext(OrganizationProto.Organizations.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void getOrganization(OrganizationProto.GetOrganizationRequest request, StreamObserver<OrganizationProto.Organization> responseObserver) {
        Optional<BdpsOrganization> bdpsOrganization = organizationRepository.findById(request.getOrganizationId());
        if (bdpsOrganization.isPresent()) {
            responseObserver.onNext(modelToRpc(bdpsOrganization.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所查找的organization(Id为" + request.getOrganizationId() + ")不存在！").asRuntimeException();
    }


    @Transactional
    @Override
    public void updateOrganization(OrganizationProto.UpdateOrganizationRequest request, StreamObserver<OrganizationProto.Organization> responseObserver) {
        if (organizationRepository.existsById(request.getOrganizationId())) {
            BdpsOrganization bdpsOrganization = Converter.create().toDomain(BdpsOrganization.class, request);
            BdpsOrganization oldbdpsOrganization = organizationRepository.getOne(bdpsOrganization.getOrganizationId());
            CopyUtils.copyProperties(bdpsOrganization, oldbdpsOrganization, true);
            EntityUtil.fill(oldbdpsOrganization);
            BdpsOrganization newbdpsOrganization = organizationRepository.save(oldbdpsOrganization);
            responseObserver.onNext(modelToRpc(newbdpsOrganization));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的Organization不存在！").asRuntimeException();
    }

    @Override
    public void deleteOrganization(OrganizationProto.DeleteOrganizationRequest request, StreamObserver<OrganizationProto.Organization> responseObserver) {
        Optional<BdpsOrganization>  organization = organizationRepository.findById(request.getOrganizationId());
        if (organization.isPresent()){
            organizationRepository.deleteById(request.getOrganizationId());
            responseObserver.onNext(modelToRpc(organization.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要删除的Organization不存在！").asRuntimeException();
    }

    private OrganizationProto.Organization modelToRpc(BdpsOrganization bdpsOrganization) {
        return Converter.create().toProtobuf(OrganizationProto.Organization.class, bdpsOrganization);
    }
}