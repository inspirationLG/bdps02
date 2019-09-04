package com.bdps.mservice.eshop.service;

import com.bdps.mservice.eshop.model.BdpsSpecialServiceItem;
import com.bdps.mservice.eshop.repository.SpecialServiceItemRepository;
import com.bdps.mservice.eshop.util.CopyUtils;
import com.bdps.mservice.eshop.util.EntityUtil;
import com.bdps.special_service_item.SpecialServiceItemProto;
import com.bdps.special_service_item.SpecialServiceItemServiceGrpc;
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
 * @CreateTime 2019/8/29 16:32
 */
@AllArgsConstructor
@GrpcService
public class SpecialServiceItemServiceImpl extends SpecialServiceItemServiceGrpc.SpecialServiceItemServiceImplBase{
    @Autowired
    private  final SpecialServiceItemRepository specialServiceItemRepository;

    //成功
    @Override
    public void getSpecialServiceItem(SpecialServiceItemProto.GetSpecialServiceItemRequest request, StreamObserver<SpecialServiceItemProto.SpecialServiceItem> responseObserver) {
        Optional<BdpsSpecialServiceItem> bdpsSpecialServiceItem = specialServiceItemRepository.findById(request.getSpecialServiceItemId());
        if (bdpsSpecialServiceItem.isPresent()){
            responseObserver.onNext(modelToRpc(bdpsSpecialServiceItem.get()));
            responseObserver.onCompleted();
        }
        else throw Status.NOT_FOUND.withDescription("所查找的SpecialServiceItem(Id为" + request.getSpecialServiceItemId() + ")不存在！").asRuntimeException();
    }

    //成功
    @Override
    public void listSpecialServiceItem(SpecialServiceItemProto.ListSpecialServiceItemRequest request, StreamObserver<SpecialServiceItemProto.SpecialServiceItems> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsSpecialServiceItem> page = specialServiceItemRepository.findAll(pageable);
        responseObserver.onNext(SpecialServiceItemProto.SpecialServiceItems.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    //成功
    @Override
    public void addSpecialServiceItem(SpecialServiceItemProto.AddSpecialServiceItemRequest request, StreamObserver<SpecialServiceItemProto.SpecialServiceItem> responseObserver) {
        Configuration configuration = Configuration.builder().addIgnoredFields(new FieldsIgnore()
                .add(BdpsSpecialServiceItem.class,"specialServiceItemId")).build();
        BdpsSpecialServiceItem bdpsSpecialServiceItem = Converter.create(configuration).toDomain(BdpsSpecialServiceItem.class, request);
        EntityUtil.fill(bdpsSpecialServiceItem);
        BdpsSpecialServiceItem newBdpsSpecialServiceItem = specialServiceItemRepository.save(bdpsSpecialServiceItem);
        responseObserver.onNext(modelToRpc(newBdpsSpecialServiceItem));
        responseObserver.onCompleted();
        //throw Status.NOT_FOUND.withDescription("添加SpecialServiceItem失败！").asRuntimeException();
    }

    //成功
    @Override
    public void updateSpecialServiceItem(SpecialServiceItemProto.UpdateSpecialServiceItemRequest request, StreamObserver<SpecialServiceItemProto.SpecialServiceItem> responseObserver) {
        Optional<BdpsSpecialServiceItem> optionalBdpsSpecialServiceItem = specialServiceItemRepository.findById(request.getSpecialServiceItemId());
        if (optionalBdpsSpecialServiceItem.isPresent()) {
            BdpsSpecialServiceItem SpecialServiceItem = optionalBdpsSpecialServiceItem.get();
            BdpsSpecialServiceItem bdpsSpecialServiceItem = Converter.create().toDomain(BdpsSpecialServiceItem.class, request);
            CopyUtils.copyProperties(bdpsSpecialServiceItem, SpecialServiceItem, true);
            BdpsSpecialServiceItem newBdpsSpecialServiceItem = specialServiceItemRepository.save(SpecialServiceItem);
            responseObserver.onNext(modelToRpc(newBdpsSpecialServiceItem));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的SpecialServiceItem不存在！").asRuntimeException();
    }

    //成功
    @Transactional
    @Override
    public void deleteSpecialServiceItem(SpecialServiceItemProto.DeleteSpecialServiceItemRequest request, StreamObserver<SpecialServiceItemProto.SpecialServiceItem> responseObserver) {
        Optional<BdpsSpecialServiceItem> optionalBdpsSpecialServiceItem = specialServiceItemRepository.findById(request.getSpecialServiceItemId());
        if (optionalBdpsSpecialServiceItem.isPresent()) {
            BdpsSpecialServiceItem bdpsSpecialServiceItem = optionalBdpsSpecialServiceItem.get();
            specialServiceItemRepository.deleteById(bdpsSpecialServiceItem.getSpecialServiceItemId());
            responseObserver.onNext(modelToRpc(bdpsSpecialServiceItem));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要删除的SpecialServiceItem不存在！").asRuntimeException();
    }

    private SpecialServiceItemProto.SpecialServiceItem modelToRpc(BdpsSpecialServiceItem bdpsSpecialServiceItem) {
        return Converter.create().toProtobuf(SpecialServiceItemProto.SpecialServiceItem.class, bdpsSpecialServiceItem);
    }
}
