package com.bdps.gateway.clients;

import com.bdps.order.OrderProto;
import com.bdps.order.OrderServiceGrpc;
import com.bdps.order_detail.OrderDetailProto;
import com.bdps.order_detail.OrderDetailServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderDetailClient {

    @GrpcClient("eshop-grpc-server")
    private OrderDetailServiceGrpc.OrderDetailServiceFutureStub orderDetailServiceFutureStub;

    public ListenableFuture<OrderDetailProto.OrderDetail> addOrderDetail(OrderDetailProto.AddOrderDetailRequest request) {
        return orderDetailServiceFutureStub.addOrderDetail(request);
    }

    public ListenableFuture<OrderDetailProto.OrderDetail> updateOrderDetail(OrderDetailProto.UpdateOrderDetailRequest request) {
        return orderDetailServiceFutureStub.updateOrderDetail(request);
    }

    public ListenableFuture<OrderDetailProto.OrderDetail> getOrderDetail(Integer id) {
        return orderDetailServiceFutureStub.getOrderDetail(OrderDetailProto.GetOrderDetailRequest.newBuilder().setOrderDetailId(id).build());
    }

    public ListenableFuture<OrderDetailProto.OrderDetail> deleteOrderDetail(OrderDetailProto.DeleteOrderDetailRequest request) {
        return orderDetailServiceFutureStub.deleteOrderDetail(request);
    }

    public ListenableFuture<OrderDetailProto.OrderDetails> listOrderDetail(OrderDetailProto.ListOrderDetailRequest request) {
        return orderDetailServiceFutureStub.listOrderDetail(request);
    }
}
