package com.bdps.gateway.resolvers.order;


import com.bdps.order.OrderProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdersResolver implements GraphQLResolver<OrderProto.Orders> {
    public List<OrderProto.Order> nodes(OrderProto.Orders orders){
        return orders.getNodesList();
    }
}
