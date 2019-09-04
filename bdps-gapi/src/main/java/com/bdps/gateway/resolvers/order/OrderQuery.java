package com.bdps.gateway.resolvers.order;


import com.bdps.gateway.clients.OrderClient;
import com.bdps.order.OrderProto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderQuery implements GraphQLQueryResolver {

    private final OrderClient orderClient;

    public ListenableFuture<OrderProto.Order> getOrder(OrderProto.GetOrderRequest request) {
        return orderClient.getOrder(request.getOrderId());
    }
    public ListenableFuture<OrderProto.Orders> listOrder(OrderProto.ListOrderRequest request) {
        return orderClient.listOrder(request);
    }
}
