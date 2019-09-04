package com.bdps.gateway.resolvers.staff_type;

import com.bdps.gateway.clients.StaffTypeClient;
import com.bdps.staff_type.StaffTypeProto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author:Hchien Ying
 * @date:2019/8/21
 * @description:
 */
@AllArgsConstructor
@Component
public class StaffTypeQuery implements GraphQLQueryResolver {
    private final StaffTypeClient staffTypeClient;

    public ListenableFuture<StaffTypeProto.StaffType> getStaffType(StaffTypeProto.GetStaffTypeRequest request) {
        return staffTypeClient.getStaffType(request.getStaffTypeId());
    }
    public ListenableFuture<StaffTypeProto.StaffTypes> listStaffType(StaffTypeProto.ListStaffTypeRequest request) {
        return staffTypeClient.listStaffType(request);
    }
}
