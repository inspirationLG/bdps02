package com.bdps.mservice.userorginfo.service;

import com.bdps.mservice.userorginfo.model.BdpsStaffType;
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
public class StaffTypeServiceIml extends StaffTypeServiceGrpc.StaffTypeServiceImplBase {
    private final StaffTypeRepository staffTypeRepository;

    @Transactional
    @Override
    public void addStaffType(StaffTypeProto.AddStaffTypeRequest request, StreamObserver<StaffTypeProto.StaffType> responseObserver) {
        Configuration configuration = Configuration.builder()
                .addIgnoredFields(new FieldsIgnore().add(BdpsStaffType.class, "staffTypeId"))
                .build();
        BdpsStaffType newStaffType = Converter.create(configuration).toDomain(BdpsStaffType.class, request);
        EntityUtil.fill(newStaffType);
        BdpsStaffType staffType = staffTypeRepository.save(newStaffType);
        responseObserver.onNext(modelToRpc(staffType));
        responseObserver.onCompleted();
        return;
    }

    @Override
    public void getStaffType(StaffTypeProto.GetStaffTypeRequest request, StreamObserver<StaffTypeProto.StaffType> responseObserver) {
        Optional<BdpsStaffType> staffType = staffTypeRepository.findById(request.getStaffTypeId());
        if (staffType.isPresent()) {
            responseObserver.onNext(modelToRpc(staffType.get()));
            responseObserver.onCompleted();
        }
        throw Status.NOT_FOUND.withDescription("未找到数据").asRuntimeException();
    }

    @Override
    public void listStaffType(StaffTypeProto.ListStaffTypeRequest request, StreamObserver<StaffTypeProto.StaffTypes> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsStaffType> page = staffTypeRepository.findAllByFlagDeletedFalse(pageable);
        responseObserver.onNext(StaffTypeProto.StaffTypes.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void updateStaffType(StaffTypeProto.UpdateStaffTypeRequest request, StreamObserver<StaffTypeProto.StaffType> responseObserver) {
        BdpsStaffType staffType = Converter.create().toDomain(BdpsStaffType.class, request);
        BdpsStaffType bdpsStaffType = staffTypeRepository.getOne(staffType.getStaffTypeId());
        CopyUtils.copyProperties(staffType, bdpsStaffType, true);
        BdpsStaffType newStaffType = staffTypeRepository.save(bdpsStaffType);
        responseObserver.onNext(modelToRpc(newStaffType));
        responseObserver.onCompleted();
        return;
    }

    private StaffTypeProto.StaffType modelToRpc(BdpsStaffType staffType) {
        return Converter.create().toProtobuf(StaffTypeProto.StaffType.class, staffType);
    }
}
