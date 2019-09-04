package com.bdps.gateway.resolvers.activity;


import com.bdps.activity.ActivityProto;
import com.bdps.staff_type.StaffTypeProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivitiesResolver implements GraphQLResolver<ActivityProto.Activities> {
    public List<ActivityProto.Activity> nodes(ActivityProto.Activities activities){
        return activities.getNodesList();
    }
}
