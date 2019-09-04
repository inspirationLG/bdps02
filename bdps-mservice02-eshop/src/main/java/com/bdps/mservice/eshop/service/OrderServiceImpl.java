package com.bdps.mservice.eshop.service;

import com.bdps.mservice.eshop.model.BdpsOrder;
import com.bdps.mservice.eshop.repository.OrderRepository;
import com.bdps.mservice.eshop.util.CopyUtils;
import com.bdps.mservice.eshop.util.EntityUtil;
import com.bdps.order.OrderProto;
import com.bdps.order.OrderServiceGrpc;
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
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase{
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public void addOrder(OrderProto.AddOrderRequest request, StreamObserver<OrderProto.Order> responseObserver) {
        Configuration configuration = Configuration.builder()
                .addIgnoredFields(new FieldsIgnore().add(BdpsOrder.class, "orderId"))
                .build();
        BdpsOrder newOrder = Converter.create(configuration).toDomain(BdpsOrder.class, request);
        EntityUtil.fill(newOrder);
        BdpsOrder order = orderRepository.save(newOrder);
        responseObserver.onNext(modelToRpc(order));
        responseObserver.onCompleted();
    }

    @Override
    public void getOrder(OrderProto.GetOrderRequest request, StreamObserver<OrderProto.Order> responseObserver) {
        Optional<BdpsOrder> order = orderRepository.findById(request.getOrderId());
        if (order.isPresent()) {
            responseObserver.onNext(modelToRpc(order.get()));
            responseObserver.onCompleted();
        }
        throw Status.NOT_FOUND.withDescription("未找到数据").asRuntimeException();
    }

    @Override
    public void listOrder(OrderProto.ListOrderRequest request, StreamObserver<OrderProto.Orders> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsOrder> page = orderRepository.findAll(pageable);
        responseObserver.onNext(OrderProto.Orders.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void updateOrder(OrderProto.UpdateOrderRequest request, StreamObserver<OrderProto.Order> responseObserver) {
        BdpsOrder order = Converter.create().toDomain(BdpsOrder.class, request);
        BdpsOrder bdpsOrder = orderRepository.getOne(order.getOrderId());
        CopyUtils.copyProperties(order, bdpsOrder, true);
        EntityUtil.fill(bdpsOrder);
        BdpsOrder newOrder = orderRepository.save(bdpsOrder);
        responseObserver.onNext(modelToRpc(newOrder));
        responseObserver.onCompleted();


    }

    private OrderProto.Order modelToRpc(BdpsOrder order) {
        return Converter.create(Configuration.builder().build())
                .toProtobuf(OrderProto.Order.class, order);
    }
}
