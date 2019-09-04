package com.bdps.gateway.resolvers.coverage_crowd_of_item;

import com.bdps.coverage_crowd_of_item.CoverageCrowdOfItemProto;
import com.bdps.gateway.clients.CoverageCrowdOfItemClient;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author zcz
 * @CreateTime 2019/8/29 13:36
 */

@AllArgsConstructor
@Component
public class CoverageCrowdOfItemQuery implements GraphQLQueryResolver {
    private final CoverageCrowdOfItemClient coverageCrowdOfItemClient;
    public ListenableFuture<CoverageCrowdOfItemProto.CoverageCrowdOfItems> listCoverageCrowdOfItem(CoverageCrowdOfItemProto.ListCoverageCrowdOfItemRequest request){
        return coverageCrowdOfItemClient.listCoverageCrowdOfItems(request);
    }
    public ListenableFuture<CoverageCrowdOfItemProto.CoverageCrowdOfItem> getCoverageCrowdOfItem(CoverageCrowdOfItemProto.GetCoverageCrowdOfItemRequest request){
        return coverageCrowdOfItemClient.getCoverageCrowdOfItem(request.getCoverageCrowdOfItemId());
    }
}
