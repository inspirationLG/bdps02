package com.bdps.gateway.resolvers.region;

import com.bdps.gateway.clients.RegionClient;
import com.bdps.region.RegionProto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author zcz
 * @CreateTime 2019/8/20 13:04
 */
@AllArgsConstructor
@Component
public class RegionQuery implements GraphQLQueryResolver {
    private final RegionClient regionClient;
    public  ListenableFuture<RegionProto.Regions>listRegion(RegionProto.ListRegionRequest request){
        return regionClient.listRegions(request);
    }

    public ListenableFuture<RegionProto.Region> getRegion(RegionProto.GetRegionRequest request){
        return regionClient.getRegion(request.getId());
    }
}