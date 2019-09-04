package com.bdps.gateway.clients;

import com.bdps.activity.ActivityProto;
import com.bdps.activity.ActivityServiceGrpc;
import com.bdps.staff_type.StaffTypeProto;
import com.bdps.staff_type.StaffTypeServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ActivityClient {

    @GrpcClient("user-org-info-grpc-server")
    private ActivityServiceGrpc.ActivityServiceFutureStub activityServiceFutureStub;

    public ListenableFuture<ActivityProto.Activity> addActivity(ActivityProto.AddActivityRequest request) {
        return activityServiceFutureStub.addActivity(request);
    }

    public ListenableFuture<ActivityProto.Activity> updateActivity(ActivityProto.UpdateActivityRequest request) {
        return activityServiceFutureStub.updateActivity(request);
    }

    public ListenableFuture<ActivityProto.Activity> getActivity(Integer id) {
        return activityServiceFutureStub.getActivity(ActivityProto.GetActivityRequest.newBuilder().setActivityId(id).build());
    }

    public ListenableFuture<ActivityProto.Activity> deleteActivity(ActivityProto.DeleteActivityRequest request) {
        return activityServiceFutureStub.deleteActivity(request);
    }

    public ListenableFuture<ActivityProto.Activities> listActivity(ActivityProto.ListActivityRequest request) {
        return activityServiceFutureStub.listActivity(request);
    }
}
