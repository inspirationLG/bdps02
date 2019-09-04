package com.bdps.mservice.userorginfo.service;

import com.bdps.equipment.EquipmentProto;
import com.bdps.equipment.EquipmentServiceGrpc;
import com.bdps.mservice.userorginfo.model.BdpsEquipment;
import com.bdps.mservice.userorginfo.repository.EquipmentRepository;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;
import lombok.AllArgsConstructor;
import net.badata.protobuf.converter.Configuration;
import net.badata.protobuf.converter.Converter;
import net.badata.protobuf.converter.FieldsIgnore;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.bdps.mservice.userorginfo.util.CopyUtils;
import com.bdps.mservice.userorginfo.util.EntityUtil;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@GrpcService
public class EquipmentImpl extends EquipmentServiceGrpc.EquipmentServiceImplBase {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Transactional
    @Override
    public void addEquipment(EquipmentProto.AddEquipmentRequest request, StreamObserver<EquipmentProto.Equipment> responseObserver) {
        Configuration configuration = Configuration.builder()
                .addIgnoredFields(new FieldsIgnore().add(BdpsEquipment.class, "equipmentId"))
                .build();
        BdpsEquipment bdpsEquipment = Converter.create(configuration).toDomain(BdpsEquipment.class, request);
        EntityUtil.fill(bdpsEquipment);
        BdpsEquipment newBdpsEquipment = equipmentRepository.save(bdpsEquipment);
        responseObserver.onNext(modelToRpc(newBdpsEquipment));
        responseObserver.onCompleted();
        return;
    }

    @Transactional
    @Override
    public void updateEquipment(EquipmentProto.UpdateEquipmentRequest request, StreamObserver<EquipmentProto.Equipment> responseObserver) {
        if (equipmentRepository.existsById(request.getEquipmentId())){
            BdpsEquipment bdpsEquipment = Converter.create().toDomain(BdpsEquipment.class,request);
            BdpsEquipment oldBdpsEquipment = equipmentRepository.getOne(bdpsEquipment.getEquipmentId());
            CopyUtils.copyProperties(bdpsEquipment, oldBdpsEquipment ,true);
            EntityUtil.fill(oldBdpsEquipment);
            BdpsEquipment newBdpsEquipment = equipmentRepository.save(oldBdpsEquipment);
            responseObserver.onNext(modelToRpc(newBdpsEquipment));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的Equipment不存在！").asRuntimeException();
    }

    @Override
    public void listEquipment(EquipmentProto.ListEquipmentRequest request, StreamObserver<EquipmentProto.Equipments> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() -1, request.getLimit());
        Page<BdpsEquipment> page = equipmentRepository.findAll(pageable);
        responseObserver.onNext(EquipmentProto.Equipments.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount(page.getNumberOfElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void getEquipment(EquipmentProto.GetEquipmentRequest request, StreamObserver<EquipmentProto.Equipment> responseObserver) {
        Optional <BdpsEquipment> bdpsEquipment = equipmentRepository.findById(request.getEquipmentId());
        if (bdpsEquipment.isPresent()){
            responseObserver.onNext(modelToRpc(bdpsEquipment.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所查找的Equipment不存在！").asRuntimeException();

    }

    @Transactional
    @Override
    public void deleteEquipment(EquipmentProto.DeleteEquipmentRequest request, StreamObserver<EquipmentProto.Equipment> responseObserver) {
        if (request.getEquipmentId() > 0 ){
            Optional<BdpsEquipment> bdpsEquipment = equipmentRepository.findById(request.getEquipmentId());
            if (bdpsEquipment.isPresent()){
                equipmentRepository.delete(bdpsEquipment.get());
                responseObserver.onNext(modelToRpc(bdpsEquipment.get()));
                responseObserver.onCompleted();
                return;
            }
        }
        throw Status.NOT_FOUND.withDescription("要删除的Equipment不存在！").asRuntimeException();

    }

    private EquipmentProto.Equipment modelToRpc(BdpsEquipment bdpsEquipment){
        return Converter.create().toProtobuf(EquipmentProto.Equipment.class,bdpsEquipment);
    }
}
