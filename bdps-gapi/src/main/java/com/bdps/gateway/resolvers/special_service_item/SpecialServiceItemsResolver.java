package com.bdps.gateway.resolvers.special_service_item;

import com.bdps.special_service_item.SpecialServiceItemProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zcz
 * @CreateTime 2019/8/29 15:39
 */
@Component
public class SpecialServiceItemsResolver implements GraphQLResolver<SpecialServiceItemProto.SpecialServiceItems> {

    public List<SpecialServiceItemProto.SpecialServiceItem> nodes(SpecialServiceItemProto.SpecialServiceItems SpecialServiceItems) {
        return SpecialServiceItems.getNodesList();
    }
}