package com.bdps.gateway.resolvers.special_service_fund;

import com.bdps.activity_staff.ActivityStaffProto;
import com.bdps.coverage_crowd.CoverageCrowdProto;
import com.bdps.gateway.clients.CoverageCrowdClient;
import com.bdps.gateway.clients.SpecialServiceFundClient;
import com.bdps.gateway.clients.StaffClient;
import com.bdps.special_service_fund.SpecialServiceFundProto;
import com.bdps.staff.StaffProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Hchien Ying
 * @date:2019/8/31
 * @description:
 */
@Component

public class SpecialServiceFundResolver implements GraphQLResolver<SpecialServiceFundProto.SpecialServiceFund> {

    @Autowired
    private CoverageCrowdClient coverageCrowdClient;

    public ListenableFuture<CoverageCrowdProto.CoverageCrowd> coveragecrowd(SpecialServiceFundProto.SpecialServiceFund specialServiceFund){
        return coverageCrowdClient.getCoverageCrowd(specialServiceFund.getCoverageCrowdId());
    }

    @Autowired
    private StaffClient staffClient;
    public ListenableFuture<StaffProto.Staff> staff(SpecialServiceFundProto.SpecialServiceFund specialServiceFund){
        return staffClient.getStaff(specialServiceFund.getStaffId());
    }

}
