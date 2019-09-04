package com.bdps.gateway.resolvers.region;

import com.bdps.gateway.clients.RegionClient;
import com.bdps.region.RegionProto;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zcz
 * @CreateTime 2019/8/20 13:04
 */
@AllArgsConstructor
@Component
public class RegionMutation implements GraphQLMutationResolver {
    @Autowired
    private final RegionClient regionClient;

    public ListenableFuture<RegionProto.Region> addRegion(RegionProto.AddRegionRequest request) {
        return regionClient.addRegion(request);
    }

    public ListenableFuture<RegionProto.Region> updateRegion(RegionProto.UpdateRegionRequest request) {
        return regionClient.updateRegion(request);
    }

    public ListenableFuture<RegionProto.Region> deleteRegion(RegionProto.DeleteRegionRequest request) {
        return regionClient.deleteRegion(request);
    }
}