package com.bdps.gateway.clients;


import com.bdps.activity.ActivityProto;
import com.bdps.activity.ActivityServiceGrpc;
import com.bdps.order.OrderProto;
import com.bdps.order.OrderServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderClient {

    @GrpcClient("eshop-grpc-server")
    private OrderServiceGrpc.OrderServiceFutureStub orderServiceFutureStub;

    public ListenableFuture<OrderProto.Order> addOrder(OrderProto.AddOrderRequest request) {
        return orderServiceFutureStub.addOrder(request);
    }

    public ListenableFuture<OrderProto.Order> updateOrder(OrderProto.UpdateOrderRequest request) {
        return orderServiceFutureStub.updateOrder(request);
    }

    public ListenableFuture<OrderProto.Order> getOrder(Integer id) {
        return orderServiceFutureStub.getOrder(OrderProto.GetOrderRequest.newBuilder().setOrderId(id).build());
    }

    public ListenableFuture<OrderProto.Order> deleteOrder(OrderProto.DeleteOrderRequest request) {
        return orderServiceFutureStub.deleteOrder(request);
    }

    public ListenableFuture<OrderProto.Orders> listOrder(OrderProto.ListOrderRequest request) {
        return orderServiceFutureStub.listOrder(request);
    }
}
