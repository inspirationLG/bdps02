package com.bdps.mservice.userorginfo.service;


import com.bdps.activity.ActivityProto;
import com.bdps.activity.ActivityServiceGrpc;
import com.bdps.mservice.userorginfo.model.BdpsActivity;
import com.bdps.mservice.userorginfo.model.BdpsRegion;
import com.bdps.mservice.userorginfo.model.BdpsStaffType;
import com.bdps.mservice.userorginfo.repository.ActivityRepository;
import com.bdps.mservice.userorginfo.repository.StaffTypeRepository;
import com.bdps.mservice.userorginfo.util.CopyUtils;
import com.bdps.mservice.userorginfo.util.EntityUtil;
import com.bdps.staff_type.StaffTypeProto;
import com.bdps.staff_type.StaffTypeServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.badata.protobuf.converter.Configuration;
import net.badata.protobuf.converter.Converter;
import net.badata.protobuf.converter.FieldsIgnore;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@GrpcService
public class ActivityServiceImpl extends ActivityServiceGrpc.ActivityServiceImplBase{


    private final ActivityRepository activityRepository;

    @Transactional
    @Override
    public void addActivity(ActivityProto.AddActivityRequest request, StreamObserver<ActivityProto.Activity> responseObserver) {
        Configuration configuration = Configuration.builder()
                .addIgnoredFields(new FieldsIgnore().add(BdpsActivity.class, "activityId"))
                .build();
        BdpsActivity newActivity = Converter.create(configuration).toDomain(BdpsActivity.class, request);
        EntityUtil.fill(newActivity);
        BdpsActivity activity = activityRepository.save(newActivity);
        responseObserver.onNext(modelToRpc(activity));
        responseObserver.onCompleted();
    }

    @Override
    public void getActivity(ActivityProto.GetActivityRequest request, StreamObserver<ActivityProto.Activity> responseObserver) {
        Optional<BdpsActivity> activity = activityRepository.findById(request.getActivityId());
        if (activity.isPresent()) {
            responseObserver.onNext(modelToRpc(activity.get()));
            responseObserver.onCompleted();
        }
        throw Status.NOT_FOUND.withDescription("未找到数据").asRuntimeException();
    }

    @Override
    public void listActivity(ActivityProto.ListActivityRequest request, StreamObserver<ActivityProto.Activities> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsActivity> page = activityRepository.findAll(pageable);
        responseObserver.onNext(ActivityProto.Activities.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void updateActivity(ActivityProto.UpdateActivityRequest request, StreamObserver<ActivityProto.Activity> responseObserver) {
        BdpsActivity activity = Converter.create().toDomain(BdpsActivity.class, request);
        BdpsActivity bdpsActivity = activityRepository.getOne(activity.getActivityId());
        CopyUtils.copyProperties(activity, bdpsActivity, true);
        EntityUtil.fill(bdpsActivity);
        BdpsActivity newActivity = activityRepository.save(bdpsActivity);
        responseObserver.onNext(modelToRpc(newActivity));
        responseObserver.onCompleted();


    }

    private ActivityProto.Activity modelToRpc(BdpsActivity activity) {
        return Converter.create(Configuration.builder().build())
                .toProtobuf(ActivityProto.Activity.class, activity);
    }
}
