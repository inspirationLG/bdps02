package com.bdps.gateway.resolvers.activity_staff;

import com.bdps.activity_staff.ActivityStaffProto;
import com.bdps.gateway.clients.ActivityStaffClient;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Hchien Ying
 * @date:2019/8/30
 * @description:
 */
@AllArgsConstructor
@Component
public class ActivityStaffQuery implements GraphQLQueryResolver {
    @Autowired
    private final ActivityStaffClient activityStaffClient;
    public ListenableFuture<ActivityStaffProto.ActivityStaffs> listActivityStaff(ActivityStaffProto.ListActivityStaffRequest request) {
        return activityStaffClient.ListActivityStaffs(request);
    }
    public ListenableFuture<ActivityStaffProto.ActivityStaff> getActivityStaff(ActivityStaffProto.GetActivityStaffRequest request) {
        return activityStaffClient.getActivityStaff(request.getActivityStaffId());
    }
}
