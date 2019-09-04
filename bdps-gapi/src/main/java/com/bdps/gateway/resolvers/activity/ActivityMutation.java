package com.bdps.gateway.resolvers.activity;


import com.bdps.activity.ActivityProto;
import com.bdps.gateway.clients.ActivityClient;
import com.bdps.gateway.clients.StaffTypeClient;
import com.bdps.staff_type.StaffTypeProto;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ActivityMutation implements GraphQLMutationResolver {
    private final ActivityClient activityClient;

    public ListenableFuture<ActivityProto.Activity> addActivity(ActivityProto.AddActivityRequest request) {
        return activityClient.addActivity(request);
    }
    public ListenableFuture<ActivityProto.Activity> updateActivity(ActivityProto.UpdateActivityRequest request) {
        return activityClient.updateActivity(request);
    }

    public ListenableFuture<ActivityProto.Activity> deleteActivity(ActivityProto.DeleteActivityRequest request) {
        return activityClient.deleteActivity(request);
    }
}
