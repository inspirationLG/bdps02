package com.bdps.gateway.resolvers.orderDetail;

import com.bdps.gateway.clients.OrderClient;
import com.bdps.gateway.clients.OrderDetailClient;
import com.bdps.order.OrderProto;
import com.bdps.order_detail.OrderDetailProto;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderDetailMutation implements GraphQLMutationResolver {
    private final OrderDetailClient orderDetailClient;

    public ListenableFuture<OrderDetailProto.OrderDetail> addOrderDetail(OrderDetailProto.AddOrderDetailRequest request) {
        return orderDetailClient.addOrderDetail(request);
    }
    public ListenableFuture<OrderDetailProto.OrderDetail> updateOrderDetail(OrderDetailProto.UpdateOrderDetailRequest request) {
        return orderDetailClient.updateOrderDetail(request);
    }

    public ListenableFuture<OrderDetailProto.OrderDetail> deleteOrderDetail(OrderDetailProto.DeleteOrderDetailRequest request) {
        return orderDetailClient.deleteOrderDetail(request);
    }
}
