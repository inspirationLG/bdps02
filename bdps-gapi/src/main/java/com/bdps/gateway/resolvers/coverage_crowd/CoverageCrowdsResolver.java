package com.bdps.gateway.resolvers.coverage_crowd;

import com.bdps.coverage_crowd.CoverageCrowdProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zcz
 * @CreateTime 2019/8/28 9:16
 */
@Component
public class CoverageCrowdsResolver implements GraphQLResolver<CoverageCrowdProto.CoverageCrowds> {

    public List<CoverageCrowdProto.CoverageCrowd> nodes(CoverageCrowdProto.CoverageCrowds coverageCrowds) {
        return coverageCrowds.getNodesList();
    }
}