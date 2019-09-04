package com.bdps.gateway.resolvers.staff;

import com.bdps.gateway.clients.StaffClient;
import com.bdps.staff.StaffProto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class StaffQuery implements GraphQLQueryResolver {

//input是参数，type是返回值
    private final StaffClient staffClient;
//    public ListenableFuture<StaffProto.Staff> getStaff(Integer staffId) {
//        return staffClient.getStaff(staffId);
//    }

    public ListenableFuture<StaffProto.Staff> getStaff(StaffProto.GetStaffRequest request) {
        return staffClient.getStaff(request.getStaffId());
    }

    public ListenableFuture<StaffProto.Staffs> listStaff(StaffProto.ListStaffRequest request){
        return staffClient.listStaff(request);
    }
}

