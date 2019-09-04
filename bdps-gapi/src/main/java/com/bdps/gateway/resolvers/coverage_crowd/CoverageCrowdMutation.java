package com.bdps.gateway.resolvers.coverage_crowd;

import com.bdps.coverage_crowd.CoverageCrowdProto;
import com.bdps.gateway.clients.CoverageCrowdClient;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @author zcz
 * @CreateTime 2019/8/28 9:15
 */
@AllArgsConstructor
@Component
public class CoverageCrowdMutation implements GraphQLMutationResolver {
    @Autowired
    private final CoverageCrowdClient coverageCrowdClient;

    public ListenableFuture<CoverageCrowdProto.CoverageCrowd> addCoverageCrowd(CoverageCrowdProto.AddCoverageCrowdRequest request){
        return coverageCrowdClient.addCoverageCrowd(request);
    }
    public ListenableFuture<CoverageCrowdProto.CoverageCrowd>updateCoverageCrowd(CoverageCrowdProto.UpdateCoverageCrowdRequest request){
        return coverageCrowdClient.updateCoverageCrowd(request);
    }

    public ListenableFuture<CoverageCrowdProto.CoverageCrowd>deleteCoverageCrowd(CoverageCrowdProto.DeleteCoverageCrowdRequest request){
        return coverageCrowdClient.deleteCoverageCrowd(request.getCoverageCrowdId());
    }
}