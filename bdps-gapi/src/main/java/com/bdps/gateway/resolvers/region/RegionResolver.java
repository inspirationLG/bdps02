package com.bdps.gateway.resolvers.region;

import com.bdps.gateway.clients.RegionClient;
import com.bdps.region.RegionProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zcz
 * @CreateTime 2019/8/20 12:45
 */
@Component
public class RegionResolver implements GraphQLResolver<RegionProto.Region> {

    @Autowired
    private RegionClient regionClient;
    public ListenableFuture<RegionProto.Region> parentRegion(RegionProto.Region region){
       // return regionClient.getRegion(RegionProto.GetRegionRequest.newBuilder().setId(region.getParentRegionId()).build());
        return regionClient.getRegion(region.getParentRegionId());

    }
}
