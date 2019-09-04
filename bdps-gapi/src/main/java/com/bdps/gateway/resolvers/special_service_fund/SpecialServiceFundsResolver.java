package com.bdps.gateway.resolvers.special_service_fund;

import com.bdps.special_service_fund.SpecialServiceFundProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Hchien Ying
 * @date:2019/8/31
 * @description:
 */
@Component

public class SpecialServiceFundsResolver implements GraphQLResolver<SpecialServiceFundProto.SpecialServiceFunds> {
    public List<SpecialServiceFundProto.SpecialServiceFund> nodes(SpecialServiceFundProto.SpecialServiceFunds specialServiceFunds) {
        return specialServiceFunds.getNodesList();
    }
}
