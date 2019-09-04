package com.bdps.gateway.resolvers.activity_staff;

import com.bdps.activity_staff.ActivityStaffProto;
import com.bdps.coverage_crowd.CoverageCrowdProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Hchien Ying
 * @date:2019/8/30
 * @description:
 */
@Component

public class ActivityStaffsResolver implements GraphQLResolver<ActivityStaffProto.ActivityStaffs> {

    public List<ActivityStaffProto.ActivityStaff> nodes(ActivityStaffProto.ActivityStaffs activityStaffs) {
        return activityStaffs.getNodesList();
    }
}
