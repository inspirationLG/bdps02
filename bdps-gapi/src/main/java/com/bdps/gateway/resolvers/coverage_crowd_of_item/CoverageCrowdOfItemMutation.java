package com.bdps.gateway.resolvers.coverage_crowd_of_item;

import com.bdps.coverage_crowd_of_item.CoverageCrowdOfItemProto;
import com.bdps.gateway.clients.CoverageCrowdOfItemClient;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zcz
 * @CreateTime 2019/8/29 13:36
 */
@AllArgsConstructor
@Component
public class CoverageCrowdOfItemMutation implements GraphQLMutationResolver {
    @Autowired
    private final CoverageCrowdOfItemClient coverageCrowdOfItemClient;

    public ListenableFuture<CoverageCrowdOfItemProto.CoverageCrowdOfItem> addCoverageCrowdOfItem(CoverageCrowdOfItemProto.AddCoverageCrowdOfItemRequest request) {
        return coverageCrowdOfItemClient.addCoverageCrowdOfItem(request);
    }

    public ListenableFuture<CoverageCrowdOfItemProto.CoverageCrowdOfItem> updateCoverageCrowdOfItem(CoverageCrowdOfItemProto.UpdateCoverageCrowdOfItemRequest request) {
        return coverageCrowdOfItemClient.updateCoverageCrowdOfItem(request);
    }

    public ListenableFuture<CoverageCrowdOfItemProto.CoverageCrowdOfItem> deleteCoverageCrowdOfItem(CoverageCrowdOfItemProto.DeleteCoverageCrowdOfItemRequest request) {
        return coverageCrowdOfItemClient.deleteCoverageCrowdOfItem(request);
    }
}
