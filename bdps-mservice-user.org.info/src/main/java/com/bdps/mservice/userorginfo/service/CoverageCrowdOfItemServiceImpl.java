package com.bdps.mservice.userorginfo.service;

import com.bdps.coverage_crowd_of_item.CoverageCrowdOfItemProto;
import com.bdps.coverage_crowd_of_item.CoverageCrowdOfItemServiceGrpc;
import com.bdps.mservice.userorginfo.model.BdpsCoverageCrowdOfItem;
import com.bdps.mservice.userorginfo.repository.CoverageCrowdOfItemRepository;
import com.bdps.mservice.userorginfo.util.CopyUtils;
import com.bdps.mservice.userorginfo.util.EntityUtil;
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
 * @author zcz
 * @CreateTime 2019/8/29 14:30
 */
@AllArgsConstructor
@GrpcService
public class CoverageCrowdOfItemServiceImpl extends CoverageCrowdOfItemServiceGrpc.CoverageCrowdOfItemServiceImplBase{
    @Autowired
    private final CoverageCrowdOfItemRepository coverageCrowdOfItemRepository;

    //成功
    @Override
    public void getCoverageCrowdOfItem(CoverageCrowdOfItemProto.GetCoverageCrowdOfItemRequest request, StreamObserver<CoverageCrowdOfItemProto.CoverageCrowdOfItem> responseObserver) {
        Optional<BdpsCoverageCrowdOfItem> bdpsCoverageCrowdOfItem = coverageCrowdOfItemRepository.findById(request.getCoverageCrowdOfItemId());
        if (bdpsCoverageCrowdOfItem.isPresent()) {
            responseObserver.onNext(modelToRpc(bdpsCoverageCrowdOfItem.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所查找的CoverageCrowdOfItem不存在！").asRuntimeException();
    }
    //成功
    @Override
    public void listCoverageCrowdOfItem(CoverageCrowdOfItemProto.ListCoverageCrowdOfItemRequest request, StreamObserver<CoverageCrowdOfItemProto.CoverageCrowdOfItems> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsCoverageCrowdOfItem> page = coverageCrowdOfItemRepository.findAll(pageable);
        responseObserver.onNext(CoverageCrowdOfItemProto.CoverageCrowdOfItems.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    //成功
    @Transactional
    @Override
    public void addCoverageCrowdOfItem(CoverageCrowdOfItemProto.AddCoverageCrowdOfItemRequest request, StreamObserver<CoverageCrowdOfItemProto.CoverageCrowdOfItem> responseObserver) {
        Configuration configuration = Configuration.builder().setIgnoredFields(new FieldsIgnore()
                .add(BdpsCoverageCrowdOfItem.class,"coverageCrowdOfItemId")).build();
        BdpsCoverageCrowdOfItem bdpsCoverageCrowdOfItem = Converter.create(configuration).toDomain(BdpsCoverageCrowdOfItem.class, request);
        EntityUtil.fill(bdpsCoverageCrowdOfItem);
        BdpsCoverageCrowdOfItem newBdpsCoverageCrowdOfItem = coverageCrowdOfItemRepository.save(bdpsCoverageCrowdOfItem);
        responseObserver.onNext(modelToRpc(newBdpsCoverageCrowdOfItem));
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void updateCoverageCrowdOfItem(CoverageCrowdOfItemProto.UpdateCoverageCrowdOfItemRequest request, StreamObserver<CoverageCrowdOfItemProto.CoverageCrowdOfItem> responseObserver) {
        Optional<BdpsCoverageCrowdOfItem> optionalBdpsCoverageCrowdOfItem = coverageCrowdOfItemRepository.findById(request.getCoverageCrowdOfItemId());
        if (optionalBdpsCoverageCrowdOfItem.isPresent()) {
            BdpsCoverageCrowdOfItem CoverageCrowdOfItem = optionalBdpsCoverageCrowdOfItem.get();
            BdpsCoverageCrowdOfItem bdpsCoverageCrowdOfItem = Converter.create().toDomain(BdpsCoverageCrowdOfItem.class, request);
            CopyUtils.copyProperties(bdpsCoverageCrowdOfItem, CoverageCrowdOfItem, true);
            BdpsCoverageCrowdOfItem newBdpsCoverageCrowdOfItem = coverageCrowdOfItemRepository.save(CoverageCrowdOfItem);
            responseObserver.onNext(modelToRpc(newBdpsCoverageCrowdOfItem));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的CoverageCrowdOfItem不存在！").asRuntimeException();
    }

    //成功
    @Transactional
    @Override
    public void deleteCoverageCrowdOfItem(CoverageCrowdOfItemProto.DeleteCoverageCrowdOfItemRequest request, StreamObserver<CoverageCrowdOfItemProto.CoverageCrowdOfItem> responseObserver) {
        Optional<BdpsCoverageCrowdOfItem> optionalBdpsCoverageCrowdOfItem = coverageCrowdOfItemRepository.findById(request.getCoverageCrowdOfItemId());
        if (optionalBdpsCoverageCrowdOfItem.isPresent()) {
            BdpsCoverageCrowdOfItem bdpsCoverageCrowdOfItem = optionalBdpsCoverageCrowdOfItem.get();
            coverageCrowdOfItemRepository.deleteById(bdpsCoverageCrowdOfItem.getCoverageCrowdOfItemId());
            responseObserver.onNext(modelToRpc(bdpsCoverageCrowdOfItem));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要删除的CoverageCrowdOfItem不存在！").asRuntimeException();

    }


    private CoverageCrowdOfItemProto.CoverageCrowdOfItem modelToRpc(BdpsCoverageCrowdOfItem bdpsCoverageCrowdOfItem) {
        return Converter.create().toProtobuf(CoverageCrowdOfItemProto.CoverageCrowdOfItem.class, bdpsCoverageCrowdOfItem);
    }
}
