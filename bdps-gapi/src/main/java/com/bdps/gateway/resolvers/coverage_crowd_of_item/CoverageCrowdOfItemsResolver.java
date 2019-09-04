package com.bdps.gateway.resolvers.coverage_crowd_of_item;

import com.bdps.coverage_crowd_of_item.CoverageCrowdOfItemProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zcz
 * @CreateTime 2019/8/29 13:36
 */
@Component
public class CoverageCrowdOfItemsResolver implements GraphQLResolver<CoverageCrowdOfItemProto.CoverageCrowdOfItems> {

    public List<CoverageCrowdOfItemProto.CoverageCrowdOfItem> nodes(CoverageCrowdOfItemProto.CoverageCrowdOfItems coverageCrowdOfItems) {
        return coverageCrowdOfItems.getNodesList();
    }
}
