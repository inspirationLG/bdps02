package com.bdps.gateway.resolvers.orderDetail;

import com.bdps.order.OrderProto;
import com.bdps.order_detail.OrderDetailProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrderDetailResolver implements GraphQLResolver<OrderDetailProto.OrderDetails> {
    public List<OrderDetailProto.OrderDetail> nodes(OrderDetailProto.OrderDetails orderDetails){
        return orderDetails.getNodesList();
    }
}
