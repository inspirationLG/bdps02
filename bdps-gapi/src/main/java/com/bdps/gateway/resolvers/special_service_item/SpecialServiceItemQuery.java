package com.bdps.gateway.resolvers.special_service_item;

import com.bdps.gateway.clients.SpecialServiceItemClient;
import com.bdps.special_service_item.SpecialServiceItemProto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author zcz
 * @CreateTime 2019/8/29 15:38
 */
@AllArgsConstructor
@Component
public class SpecialServiceItemQuery implements GraphQLQueryResolver {
    private final SpecialServiceItemClient specialServiceItemClient;
    public ListenableFuture<SpecialServiceItemProto.SpecialServiceItems> listSpecialServiceItem(SpecialServiceItemProto.ListSpecialServiceItemRequest request){
        return specialServiceItemClient.listSpecialServiceItems(request);
    }

    public ListenableFuture<SpecialServiceItemProto.SpecialServiceItem> getSpecialServiceItem(SpecialServiceItemProto.GetSpecialServiceItemRequest request){
        return specialServiceItemClient.getSpecialServiceItem(request.getSpecialServiceItemId());
    }
}