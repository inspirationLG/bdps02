package com.bdps.gateway.resolvers.coverage_crowd_of_item;

import com.bdps.coverage_crowd.CoverageCrowdProto;
import com.bdps.coverage_crowd_of_item.CoverageCrowdOfItemProto;
import com.bdps.gateway.clients.CoverageCrowdClient;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zcz
 * @CreateTime 2019/8/29 13:36
 */
@Component
public class CoverageCrowdOfItemResolver implements GraphQLResolver<CoverageCrowdOfItemProto.CoverageCrowdOfItem> {

    @Autowired
    private CoverageCrowdClient coverageCrowdClient;
    public ListenableFuture<CoverageCrowdProto.CoverageCrowd> coverageCrowd(CoverageCrowdOfItemProto.CoverageCrowdOfItem coverageCrowdOfItem){
        return coverageCrowdClient.getCoverageCrowd(coverageCrowdOfItem.getCoverageCrowdId());
    }
}