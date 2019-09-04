package com.bdps.gateway.resolvers.staff;

import com.bdps.gateway.clients.StaffClient;
import com.bdps.staff.StaffProto;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class StaffMutation implements GraphQLMutationResolver {

    private final StaffClient staffClient;

    public ListenableFuture<StaffProto.Staff> addStaff(StaffProto.AddStaffRequest request){
        return staffClient.addStaff(request);
    }

    public ListenableFuture<StaffProto.Staff> updateStaff(StaffProto.UpdateStaffRequest request){
        return staffClient.updateStaff(request);
    }

    public ListenableFuture<StaffProto.Staff> deleteStaff(StaffProto.DeleteStaffRequest request){
        return staffClient.deleteStaff(request);
    }
}
