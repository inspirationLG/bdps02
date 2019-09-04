package com.bdps.gateway.resolvers.activity;

import com.bdps.activity.ActivityProto;
import com.bdps.gateway.clients.ActivityClient;
import com.bdps.gateway.clients.StaffTypeClient;
import com.bdps.staff_type.StaffTypeProto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ActivityQuery  implements GraphQLQueryResolver {

    private final ActivityClient activityClient;

    public ListenableFuture<ActivityProto.Activity> getActivity(ActivityProto.GetActivityRequest request) {
        return activityClient.getActivity(request.getActivityId());
    }
    public ListenableFuture<ActivityProto.Activities> listActivity(ActivityProto.ListActivityRequest request) {
        return activityClient.listActivity(request);
    }
}
