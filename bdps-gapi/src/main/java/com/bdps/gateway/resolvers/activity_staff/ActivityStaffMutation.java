package com.bdps.gateway.resolvers.activity_staff;

import com.bdps.activity_staff.ActivityStaffProto;
import com.bdps.gateway.clients.ActivityStaffClient;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
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
public class ActivityStaffMutation implements GraphQLMutationResolver {
    @Autowired
    private final ActivityStaffClient activityStaffClient;

    public ListenableFuture<ActivityStaffProto.ActivityStaff> addActivityStaff(ActivityStaffProto.AddActivityStaffRequest request) {
        return activityStaffClient.addActivityStaff(request);
    }
    public ListenableFuture<ActivityStaffProto.ActivityStaff> updateActivityStaff(ActivityStaffProto.UpdateActivityStaffRequest request) {
        return activityStaffClient.updateActivityStaff(request);
    }
    public ListenableFuture<ActivityStaffProto.ActivityStaff> deleteActivityStaff(ActivityStaffProto.DeleteActivityStaffRequest request) {
        return activityStaffClient.deleteActivityStaff(request.getActivityStaffId());
    }

}
