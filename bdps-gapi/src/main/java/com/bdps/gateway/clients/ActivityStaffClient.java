package com.bdps.gateway.clients;

import com.bdps.activity_staff.ActivityStaffProto;
import com.bdps.activity_staff.ActivityStaffServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @Author:Hchien Ying
 * @date:2019/8/30
 * @description:
 */
@Service
public class ActivityStaffClient {
    @GrpcClient("user-org-info-grpc-server")
    private ActivityStaffServiceGrpc.ActivityStaffServiceFutureStub activityStaffServiceFutureStub;
    public ListenableFuture<ActivityStaffProto.ActivityStaff> addActivityStaff(ActivityStaffProto.AddActivityStaffRequest request) {
        return activityStaffServiceFutureStub.addActivityStaff(request);
    }
    public ListenableFuture<ActivityStaffProto.ActivityStaff> updateActivityStaff(ActivityStaffProto.UpdateActivityStaffRequest request) {
        return activityStaffServiceFutureStub.updateActivityStaff(request);
    }
    public ListenableFuture<ActivityStaffProto.ActivityStaffs> ListActivityStaffs(ActivityStaffProto.ListActivityStaffRequest request) {
        return activityStaffServiceFutureStub.listActivityStaff(request);
    }
    public ListenableFuture<ActivityStaffProto.ActivityStaff> getActivityStaff(Integer activityStaffId) {
        return activityStaffServiceFutureStub.getActivityStaff(ActivityStaffProto.GetActivityStaffRequest.newBuilder().setActivityStaffId(activityStaffId).build());
    }
    public ListenableFuture<ActivityStaffProto.ActivityStaff> deleteActivityStaff(Integer activityStaffId) {
        return activityStaffServiceFutureStub.deleteActivityStaff(ActivityStaffProto.DeleteActivityStaffRequest.newBuilder().setActivityStaffId(activityStaffId).build());
    }
}
