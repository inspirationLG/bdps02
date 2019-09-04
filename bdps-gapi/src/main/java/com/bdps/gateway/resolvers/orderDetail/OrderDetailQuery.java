package com.bdps.gateway.resolvers.orderDetail;

import com.bdps.gateway.clients.OrderClient;
import com.bdps.gateway.clients.OrderDetailClient;
import com.bdps.order.OrderProto;
import com.bdps.order_detail.OrderDetailProto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderDetailQuery implements GraphQLQueryResolver {
    private final OrderDetailClient orderDetailClient;

    public ListenableFuture<OrderDetailProto.OrderDetail> getOrderDetail(OrderDetailProto.GetOrderDetailRequest request) {
        return orderDetailClient.getOrderDetail(request.getOrderDetailId());
    }
    public ListenableFuture<OrderDetailProto.OrderDetails> listOrderDetail(OrderDetailProto.ListOrderDetailRequest request) {
        return orderDetailClient.listOrderDetail(request);
    }
}
