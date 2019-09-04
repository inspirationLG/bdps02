package com.bdps.mservice.userorginfo.service;

import com.bdps.activity_staff.ActivityStaffProto;
import com.bdps.activity_staff.ActivityStaffServiceGrpc;
import com.bdps.coverage_crowd.CoverageCrowdProto;
import com.bdps.mservice.userorginfo.model.BdpsActivityStaff;
import com.bdps.mservice.userorginfo.model.BdpsCoverageCrowd;
import com.bdps.mservice.userorginfo.model.BdpsRegion;
import com.bdps.mservice.userorginfo.repository.ActivityStaffRepository;
import com.bdps.mservice.userorginfo.util.CopyUtils;
import com.bdps.mservice.userorginfo.util.EntityUtil;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.badata.protobuf.converter.Configuration;
import net.badata.protobuf.converter.Converter;
import net.badata.protobuf.converter.FieldsIgnore;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @Author:Hchien Ying
 * @date:2019/8/30
 * @description:
 */
@AllArgsConstructor
@GrpcService
public class ActivityStaffImpl extends ActivityStaffServiceGrpc.ActivityStaffServiceImplBase {
    @Autowired
    private final ActivityStaffRepository activityStaffRepository;

        @Transactional
        @Override
    public void addActivityStaff(ActivityStaffProto.AddActivityStaffRequest request, StreamObserver<ActivityStaffProto.ActivityStaff> responseObserver) {
        Configuration configuration = Configuration.builder()
                .addIgnoredFields(new FieldsIgnore().add(BdpsActivityStaff.class, "activityStaffId"))
                .build();
        BdpsActivityStaff bdpsActivityStaff = Converter.create(configuration).toDomain(BdpsActivityStaff.class, request);
        EntityUtil.fill(bdpsActivityStaff);
        BdpsActivityStaff newbdpsActivityStaff = activityStaffRepository.save(bdpsActivityStaff);
        responseObserver.onNext(modelToRpc(newbdpsActivityStaff));
        responseObserver.onCompleted();
        return;    }

    @Override
    public void listActivityStaff(ActivityStaffProto.ListActivityStaffRequest request, StreamObserver<ActivityStaffProto.ActivityStaffs> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsActivityStaff> page = activityStaffRepository.findAll(pageable);
        responseObserver.onNext(ActivityStaffProto.ActivityStaffs.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();    }

    @Override
    public void getActivityStaff(ActivityStaffProto.GetActivityStaffRequest request, StreamObserver<ActivityStaffProto.ActivityStaff> responseObserver) {
        Optional<BdpsActivityStaff> bdpsActivityStaff = activityStaffRepository.findById(request.getActivityStaffId());
        if (bdpsActivityStaff.isPresent()) {
            responseObserver.onNext(modelToRpc(bdpsActivityStaff.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所查找的activityStaff不存在！").asRuntimeException();    }

    @Transactional
    @Override
    public void updateActivityStaff(ActivityStaffProto.UpdateActivityStaffRequest request, StreamObserver<ActivityStaffProto.ActivityStaff> responseObserver) {
        Optional<BdpsActivityStaff> optionalBdpsActivityStaff = activityStaffRepository.findById(request.getActivityStaffId());
        if (optionalBdpsActivityStaff.isPresent()) {
            BdpsActivityStaff activityStaff = optionalBdpsActivityStaff.get();
            BdpsActivityStaff bdpsActivityStaff = Converter.create().toDomain(BdpsActivityStaff.class, request);
            CopyUtils.copyProperties(bdpsActivityStaff, activityStaff, true);
            BdpsActivityStaff newBdpsActivityStaff = activityStaffRepository.save(activityStaff);
            responseObserver.onNext(modelToRpc(newBdpsActivityStaff));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的activityStaff不存在！").asRuntimeException();    }

    @Transactional
    @Override
    public void deleteActivityStaff(ActivityStaffProto.DeleteActivityStaffRequest request, StreamObserver<ActivityStaffProto.ActivityStaff> responseObserver) {
        Optional<BdpsActivityStaff> optionalBdpsActivityStaff = activityStaffRepository.findById(request.getActivityStaffId());
        if (optionalBdpsActivityStaff.isPresent()) {
            BdpsActivityStaff bdpsActivityStaff = optionalBdpsActivityStaff.get();
            activityStaffRepository.deleteById(bdpsActivityStaff.getActivityStaffId());
            responseObserver.onNext(modelToRpc(bdpsActivityStaff));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要删除的ActivityStaff不存在！").asRuntimeException();

    }

    private ActivityStaffProto.ActivityStaff modelToRpc(BdpsActivityStaff bdpsActivityStaff) {
        return Converter.create().toProtobuf(ActivityStaffProto.ActivityStaff.class, bdpsActivityStaff);
    }
}
