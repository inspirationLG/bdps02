package com.bdps.gateway.resolvers.region;

import com.bdps.region.RegionProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zcz
 * @CreateTime 2019/8/20 12:49
 */
@Component
public class RegionsResolver implements GraphQLResolver<RegionProto.Regions> {

    public List<RegionProto.Region> nodes(RegionProto.Regions regions) {
        return regions.getNodesList();
    }
}
