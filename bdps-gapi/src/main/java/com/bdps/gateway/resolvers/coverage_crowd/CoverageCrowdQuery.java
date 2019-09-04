package com.bdps.gateway.resolvers.coverage_crowd;

import com.bdps.coverage_crowd.CoverageCrowdProto;
import com.bdps.gateway.clients.CoverageCrowdClient;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zcz
 * @CreateTime 2019/8/28 9:16
 */
@AllArgsConstructor
@Component
public class CoverageCrowdQuery implements GraphQLQueryResolver {
    @Autowired
    private final CoverageCrowdClient coverageCrowdClient;
    public ListenableFuture<CoverageCrowdProto.CoverageCrowds> listCoverageCrowd(CoverageCrowdProto.ListCoverageCrowdRequest request){
        return coverageCrowdClient.listCoverageCrowds(request);
    }
    public ListenableFuture<CoverageCrowdProto.CoverageCrowd> getCoverageCrowd(CoverageCrowdProto.GetCoverageCrowdRequest request){
        return coverageCrowdClient.getCoverageCrowd(request.getCoverageCrowdId());
    }
}
