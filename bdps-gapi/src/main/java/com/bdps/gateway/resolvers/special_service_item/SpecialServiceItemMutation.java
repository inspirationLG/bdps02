package com.bdps.gateway.resolvers.special_service_item;

import com.bdps.gateway.clients.SpecialServiceItemClient;
import com.bdps.special_service_item.SpecialServiceItemProto;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zcz
 * @CreateTime 2019/8/29 15:39
 */
@AllArgsConstructor
@Component
public class SpecialServiceItemMutation implements GraphQLMutationResolver {
    @Autowired
    private final SpecialServiceItemClient specialServiceItemClient;

    public ListenableFuture<SpecialServiceItemProto.SpecialServiceItem> addSpecialServiceItem(SpecialServiceItemProto.AddSpecialServiceItemRequest request) {
        return specialServiceItemClient.addSpecialServiceItem(request);
    }

    public ListenableFuture<SpecialServiceItemProto.SpecialServiceItem> updateSpecialServiceItem(SpecialServiceItemProto.UpdateSpecialServiceItemRequest request) {
        return specialServiceItemClient.updateSpecialServiceItem(request);
    }

    public ListenableFuture<SpecialServiceItemProto.SpecialServiceItem> deleteSpecialServiceItem(SpecialServiceItemProto.DeleteSpecialServiceItemRequest request) {
        return specialServiceItemClient.deleteSpecialServiceItem(request);
    }
}