package com.bdps.gateway.resolvers.order;


import com.bdps.gateway.clients.OrderClient;
import com.bdps.order.OrderProto;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderMutation implements GraphQLMutationResolver {

    private final OrderClient orderClient;

    public ListenableFuture<OrderProto.Order> addOrder(OrderProto.AddOrderRequest request) {
        return orderClient.addOrder(request);
    }
    public ListenableFuture<OrderProto.Order> updateOrder(OrderProto.UpdateOrderRequest request) {
        return orderClient.updateOrder(request);
    }

    public ListenableFuture<OrderProto.Order> deleteOrder(OrderProto.DeleteOrderRequest request) {
        return orderClient.deleteOrder(request);
    }
}
