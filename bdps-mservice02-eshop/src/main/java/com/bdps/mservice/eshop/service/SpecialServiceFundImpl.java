package com.bdps.mservice.eshop.service;

import com.bdps.mservice.eshop.model.BdpsSpecialServiceFund;
import com.bdps.mservice.eshop.repository.SpecialServiceFundRepository;
import com.bdps.mservice.eshop.util.CopyUtils;
import com.bdps.mservice.eshop.util.EntityUtil;
import com.bdps.special_service_fund.SpecialServiceFundProto;
import com.bdps.special_service_fund.SpecialServiceFundServiceGrpc;
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
public class SpecialServiceFundImpl extends SpecialServiceFundServiceGrpc.SpecialServiceFundServiceImplBase {
    @Autowired
    private final SpecialServiceFundRepository specialServiceFundRepository;

    @Transactional
    @Override
    public void addSpecialServiceFund(SpecialServiceFundProto.AddSpecialServiceFundRequest request, StreamObserver<SpecialServiceFundProto.SpecialServiceFund> responseObserver) {
        Configuration configuration = Configuration.builder()
                .addIgnoredFields(new FieldsIgnore().add(BdpsSpecialServiceFund.class, "specialServiceFundId"))
                .build();
        BdpsSpecialServiceFund bdpsSpecialServiceFund = Converter.create(configuration).toDomain(BdpsSpecialServiceFund.class, request);
        EntityUtil.fill(bdpsSpecialServiceFund);
        BdpsSpecialServiceFund newbdpsSpecialServiceFund = specialServiceFundRepository.save(bdpsSpecialServiceFund);
        responseObserver.onNext(modelToRpc(newbdpsSpecialServiceFund));
        responseObserver.onCompleted();
        return;
    }

    @Override
    public void listSpecialServiceFund(SpecialServiceFundProto.ListSpecialServiceFundRequest request, StreamObserver<SpecialServiceFundProto.SpecialServiceFunds> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsSpecialServiceFund> page = specialServiceFundRepository.findAll(pageable);
        responseObserver.onNext(SpecialServiceFundProto.SpecialServiceFunds.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();    }

    @Override
    public void getSpecialServiceFund(SpecialServiceFundProto.GetSpecialServiceFundRequest request, StreamObserver<SpecialServiceFundProto.SpecialServiceFund> responseObserver) {
        Optional<BdpsSpecialServiceFund> bdpsSpecialServiceFund = specialServiceFundRepository.findById(request.getSpecialServiceFundId());
        if (bdpsSpecialServiceFund.isPresent()) {
            responseObserver.onNext(modelToRpc(bdpsSpecialServiceFund.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所查找的bdpsSpecialServiceFund不存在！").asRuntimeException();    }

    @Transactional
    @Override
    public void updateSpecialServiceFund(SpecialServiceFundProto.UpdateSpecialServiceFundRequest request, StreamObserver<SpecialServiceFundProto.SpecialServiceFund> responseObserver) {
        Optional<BdpsSpecialServiceFund> optionalBdpsSpecialServiceFund = specialServiceFundRepository.findById(request.getSpecialServiceFundId());
        if (optionalBdpsSpecialServiceFund.isPresent()) {
            BdpsSpecialServiceFund specialServiceFund = optionalBdpsSpecialServiceFund.get();
            BdpsSpecialServiceFund bdpsSpecialServiceFund = Converter.create().toDomain(BdpsSpecialServiceFund.class, request);
            CopyUtils.copyProperties(bdpsSpecialServiceFund, specialServiceFund, true);
            BdpsSpecialServiceFund newBdpsSpecialServiceFund = specialServiceFundRepository.save(specialServiceFund);
            responseObserver.onNext(modelToRpc(newBdpsSpecialServiceFund));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的BdpsSpecialServiceFund不存在！").asRuntimeException();    }

    @Override
    @Transactional
    public void deleteSpecialServiceFund(SpecialServiceFundProto.DeleteSpecialServiceFundRequest request, StreamObserver<SpecialServiceFundProto.SpecialServiceFund> responseObserver) {
        Optional<BdpsSpecialServiceFund> optionalBdpsSpecialServiceFund = specialServiceFundRepository.findById(request.getSpecialServiceFundId());
        if (optionalBdpsSpecialServiceFund.isPresent()) {
            BdpsSpecialServiceFund bdpsSpecialServiceFund = optionalBdpsSpecialServiceFund.get();
            specialServiceFundRepository.deleteById(bdpsSpecialServiceFund.getSpecialServiceFundId());
            responseObserver.onNext(modelToRpc(bdpsSpecialServiceFund));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要删除的SpecialServiceFund不存在！").asRuntimeException();
    }


    private SpecialServiceFundProto.SpecialServiceFund modelToRpc(BdpsSpecialServiceFund bdpsSpecialServiceFund) {
        return Converter.create().toProtobuf(SpecialServiceFundProto.SpecialServiceFund.class, bdpsSpecialServiceFund);
    }
}
