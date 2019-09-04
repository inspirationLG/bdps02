package com.bdps.gateway.resolvers.staff_type;

import com.bdps.gateway.clients.StaffTypeClient;
import com.bdps.staff_type.StaffTypeProto;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
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
public class StaffTypeMutation implements GraphQLMutationResolver {
    private final StaffTypeClient staffTypeClient;

    public ListenableFuture<StaffTypeProto.StaffType> addStaffType(StaffTypeProto.AddStaffTypeRequest request) {
        return staffTypeClient.addStaffType(request);
    }
    public ListenableFuture<StaffTypeProto.StaffType> updateStaffType(StaffTypeProto.UpdateStaffTypeRequest request) {
        return staffTypeClient.updateStaffType(request);
    }

    public ListenableFuture<StaffTypeProto.StaffType> deleteStaffType(StaffTypeProto.DeleteStaffTypeRequest request) {
        return staffTypeClient.deleteStaffType(request);
    }
}

