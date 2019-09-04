package com.bdps.mservice.eshop.service;

import com.bdps.mservice.eshop.model.BdpsSpecialService;
import com.bdps.mservice.eshop.repository.SpecialServiceRepository;
import com.bdps.mservice.eshop.util.CopyUtils;
import com.bdps.mservice.eshop.util.EntityUtil;
import com.bdps.special_service.SpecialServiceProto;
import com.bdps.special_service.SpecialServiceServiceGrpc;
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

/**
 * @Author:Hchien Ying
 * @date:2019/9/1
 * @description:
 */
@AllArgsConstructor
@GrpcService
public class SpecialServiceImpl extends SpecialServiceServiceGrpc.SpecialServiceServiceImplBase{

    @Autowired
    private final SpecialServiceRepository specialServiceRepository;

    @Override
    @Transactional
    public void addSpecialService(SpecialServiceProto.AddSpecialServiceRequest request, StreamObserver<SpecialServiceProto.SpecialService> responseObserver) {
        Configuration configuration = Configuration.builder()
                .addIgnoredFields(new FieldsIgnore().add(BdpsSpecialService.class, "specialServiceId"))
                .build();
        BdpsSpecialService bdpsSpecialService = Converter.create(configuration).toDomain(BdpsSpecialService.class, request);
        EntityUtil.fill(bdpsSpecialService);
        BdpsSpecialService newbdpsSpecialService = specialServiceRepository.save(bdpsSpecialService);
        responseObserver.onNext(modelToRpc(newbdpsSpecialService));
        responseObserver.onCompleted();
        return;
    }

    @Override
    public void listSpecialService(SpecialServiceProto.ListSpecialServiceRequest request, StreamObserver<SpecialServiceProto.SpecialServices> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsSpecialService> page = specialServiceRepository.findAll(pageable);
        responseObserver.onNext(SpecialServiceProto.SpecialServices.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void getSpecialService(SpecialServiceProto.GetSpecialServiceRequest request, StreamObserver<SpecialServiceProto.SpecialService> responseObserver) {
        Optional<BdpsSpecialService> bdpsSpecialService = specialServiceRepository.findById(request.getSpecialServiceId());
        if (bdpsSpecialService.isPresent()) {
            responseObserver.onNext(modelToRpc(bdpsSpecialService.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所查找的bdpsSpecialService不存在！").asRuntimeException();    }

    @Transactional
    @Override
    public void updateSpecialService(SpecialServiceProto.UpdateSpecialServiceRequest request, StreamObserver<SpecialServiceProto.SpecialService> responseObserver) {
        Optional<BdpsSpecialService> optionalBdpsSpecialService = specialServiceRepository.findById(request.getSpecialServiceId());
        if (optionalBdpsSpecialService.isPresent()) {
            BdpsSpecialService specialService = optionalBdpsSpecialService.get();
            BdpsSpecialService bdpsSpecialService = Converter.create().toDomain(BdpsSpecialService.class, request);
            CopyUtils.copyProperties(bdpsSpecialService, specialService, true);
            BdpsSpecialService newBdpsSpecialService = specialServiceRepository.save(specialService);
            responseObserver.onNext(modelToRpc(newBdpsSpecialService));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的BdpsSpecialService不存在！").asRuntimeException();    }

    @Override
    @Transactional
    public void deleteSpecialService(SpecialServiceProto.DeleteSpecialServiceRequest request, StreamObserver<SpecialServiceProto.SpecialService> responseObserver) {
        Optional<BdpsSpecialService> optionalBdpsSpecialService = specialServiceRepository.findById(request.getSpecialServiceId());
        if (optionalBdpsSpecialService.isPresent()) {
            BdpsSpecialService bdpsSpecialService = optionalBdpsSpecialService.get();
            specialServiceRepository.deleteById(bdpsSpecialService.getSpecialServiceId());
            responseObserver.onNext(modelToRpc(bdpsSpecialService));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要删除的SpecialService不存在！").asRuntimeException();
    }

    private SpecialServiceProto.SpecialService modelToRpc(BdpsSpecialService bdpsSpecialService) {
        return Converter.create().toProtobuf(SpecialServiceProto.SpecialService.class, bdpsSpecialService);
    }

}
