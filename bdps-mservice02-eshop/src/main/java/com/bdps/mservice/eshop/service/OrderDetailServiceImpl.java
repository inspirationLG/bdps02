package com.bdps.mservice.eshop.service;

import com.bdps.mservice.eshop.model.BdpsOrder;
import com.bdps.mservice.eshop.model.BdpsOrderDetail;
import com.bdps.mservice.eshop.repository.OrderDetailRepository;
import com.bdps.mservice.eshop.repository.OrderRepository;
import com.bdps.mservice.eshop.util.CopyUtils;
import com.bdps.mservice.eshop.util.EntityUtil;
import com.bdps.order.OrderProto;
import com.bdps.order_detail.OrderDetailProto;
import com.bdps.order_detail.OrderDetailServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.badata.protobuf.converter.Configuration;
import net.badata.protobuf.converter.Converter;
import net.badata.protobuf.converter.FieldsIgnore;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@GrpcService
public class OrderDetailServiceImpl extends OrderDetailServiceGrpc.OrderDetailServiceImplBase{


    private final OrderDetailRepository orderDetailRepository;

    @Transactional
    @Override
    public void addOrderDetail(OrderDetailProto.AddOrderDetailRequest request, StreamObserver<OrderDetailProto.OrderDetail> responseObserver) {
        Configuration configuration = Configuration.builder()
                .addIgnoredFields(new FieldsIgnore().add(BdpsOrderDetail.class, "orderDetailId"))
                .build();
        BdpsOrderDetail newOrderDetail = Converter.create(configuration).toDomain(BdpsOrderDetail.class, request);
        EntityUtil.fill(newOrderDetail);
        BdpsOrderDetail orderDetail = orderDetailRepository.save(newOrderDetail);
        responseObserver.onNext(modelToRpc(orderDetail));
        responseObserver.onCompleted();
    }

    @Override
    public void getOrderDetail(OrderDetailProto.GetOrderDetailRequest request, StreamObserver<OrderDetailProto.OrderDetail> responseObserver) {
        Optional<BdpsOrderDetail> orderDetail = orderDetailRepository.findById(request.getOrderDetailId());
        if (orderDetail.isPresent()) {
            responseObserver.onNext(modelToRpc(orderDetail.get()));
            responseObserver.onCompleted();
        }
        throw Status.NOT_FOUND.withDescription("未找到数据").asRuntimeException();
    }

    @Override
    public void listOrderDetail(OrderDetailProto.ListOrderDetailRequest request, StreamObserver<OrderDetailProto.OrderDetails> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsOrderDetail> page = orderDetailRepository.findAll(pageable);
        responseObserver.onNext(OrderDetailProto.OrderDetails.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void updateOrderDetail(OrderDetailProto.UpdateOrderDetailRequest request, StreamObserver<OrderDetailProto.OrderDetail> responseObserver) {
        BdpsOrderDetail orderDetail = Converter.create().toDomain(BdpsOrderDetail.class, request);
        BdpsOrderDetail bdpsOrderDetail = orderDetailRepository.getOne(orderDetail.getOrderDetailId());
        CopyUtils.copyProperties(orderDetail, bdpsOrderDetail, true);
        EntityUtil.fill(bdpsOrderDetail);
        BdpsOrderDetail newOrderDetail = orderDetailRepository.save(bdpsOrderDetail);
        responseObserver.onNext(modelToRpc(newOrderDetail));
        responseObserver.onCompleted();


    }

    private OrderDetailProto.OrderDetail modelToRpc(BdpsOrderDetail orderDetail) {
        return Converter.create(Configuration.builder().build())
                .toProtobuf(OrderDetailProto.OrderDetail.class, orderDetail);
    }
}
