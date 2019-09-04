package com.bdps.mservice.userorginfo.service;

import com.bdps.mservice.userorginfo.model.BdpsStaff;
import com.bdps.mservice.userorginfo.repository.StaffRepository;
import com.bdps.mservice.userorginfo.util.CopyUtils;
import com.bdps.mservice.userorginfo.util.EntityUtil;
import com.bdps.staff.StaffProto;
import com.bdps.staff.StaffServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.badata.protobuf.converter.Converter;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@GrpcService
public class StaffServiceImpl extends StaffServiceGrpc.StaffServiceImplBase {
    @Autowired
    private StaffRepository staffRepository;

    @Transactional
    @Override
    public void addStaff(StaffProto.AddStaffRequest request, StreamObserver<StaffProto.Staff> responseObserver) {
        int parentStaffId = request.getParentStaffId();
        if(parentStaffId == 0 || staffRepository.existsById(parentStaffId)){
            BdpsStaff newBdpsStaff = Converter.create().toDomain(BdpsStaff.class, request);
            EntityUtil.fill(newBdpsStaff);
            newBdpsStaff.setParentStaffId(parentStaffId);
            BdpsStaff bdpsStaff = staffRepository.save(newBdpsStaff);
            responseObserver.onNext(modelToRpc(bdpsStaff));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("parentStaff不存在！").asRuntimeException();

    }

    @Override
    public void getStaff(StaffProto.GetStaffRequest request, StreamObserver <StaffProto.Staff> responseObserver) {
        Optional <BdpsStaff> bdpsStaff = staffRepository.findById(request.getStaffId());
        if (bdpsStaff.isPresent()){
            responseObserver.onNext(modelToRpc(bdpsStaff.get()));
            responseObserver.onCompleted();
        }
        throw Status.NOT_FOUND.withDescription("所查找的Staff不存在！").asRuntimeException();
    }

    @Transactional
    @Override
    public void updateStaff(StaffProto.UpdateStaffRequest request, StreamObserver<StaffProto.Staff> responseObserver) {
        if (staffRepository.existsById(request.getStaffId())){
            BdpsStaff bdpsStaff = Converter.create().toDomain(BdpsStaff.class,request);
            BdpsStaff oldBdpsStaff = staffRepository.getOne(bdpsStaff.getStaffId());
            CopyUtils.copyProperties(bdpsStaff, oldBdpsStaff ,true);
            EntityUtil.fill(oldBdpsStaff);
            BdpsStaff newBdpsStaff = staffRepository.save(oldBdpsStaff);
            responseObserver.onNext(modelToRpc(newBdpsStaff));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的Staff不存在！").asRuntimeException();
    }

    @Override
    public void listStaff(StaffProto.ListStaffRequest request, StreamObserver <StaffProto.Staffs> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsStaff> page = staffRepository.findAll(pageable);
        responseObserver.onNext(StaffProto.Staffs.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void deleteStaff(StaffProto.DeleteStaffRequest request, StreamObserver<StaffProto.Staff> responseObserver) {
        if (request.getStaffId() > 0 ){
            Optional<BdpsStaff> bdpsStaff = staffRepository.findById(request.getStaffId());
            if (bdpsStaff.isPresent()){
                staffRepository.delete(bdpsStaff.get());
                responseObserver.onNext(modelToRpc(bdpsStaff.get()));
                responseObserver.onCompleted();
                return;
            }
        }
        throw Status.NOT_FOUND.withDescription("要删除的Staff不存在！").asRuntimeException();
    }

    private StaffProto.Staff modelToRpc(BdpsStaff bdpsStaff){
        return Converter.create().toProtobuf(StaffProto.Staff.class,bdpsStaff);
    }
}


