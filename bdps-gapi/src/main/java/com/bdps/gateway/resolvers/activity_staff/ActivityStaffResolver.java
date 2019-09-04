package com.bdps.gateway.resolvers.activity_staff;

import com.bdps.activity_staff.ActivityStaffProto;
import com.bdps.gateway.clients.StaffClient;
import com.bdps.staff.StaffProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Hchien Ying
 * @date:2019/8/30
 * @description:
 */
@Component

public class ActivityStaffResolver implements GraphQLResolver<ActivityStaffProto.ActivityStaff> {
    @Autowired
    private StaffClient staffClient;
    public ListenableFuture<StaffProto.Staff> staff(ActivityStaffProto.ActivityStaff activityStaff){
        // return regionClient.getRegion(RegionProto.GetRegionRequest.newBuilder().setId(region.getParentRegionId()).build());
        return staffClient.getStaff(activityStaff.getStaffId());

    }
}
