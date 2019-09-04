package com.bdps.gateway.clients;

import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import com.bdps.equipment.EquipmentProto;
import com.bdps.equipment.EquipmentServiceGrpc;

@Service
public class EquipmentClient {
    @GrpcClient("user-org-info-grpc-server")
    private EquipmentServiceGrpc.EquipmentServiceFutureStub equipmentServiceFutureStub;

    public ListenableFuture<EquipmentProto.Equipment> addEquipment(EquipmentProto.AddEquipmentRequest request){
        return equipmentServiceFutureStub.addEquipment(request);
    }

    public ListenableFuture<EquipmentProto.Equipments> listEquipment(EquipmentProto.ListEquipmentRequest request){
        return equipmentServiceFutureStub.listEquipment(request);
    }

    public ListenableFuture<EquipmentProto.Equipment> updateEquipment(EquipmentProto.UpdateEquipmentRequest request){
        return equipmentServiceFutureStub.updateEquipment(request);
    }

    public ListenableFuture <EquipmentProto.Equipment> deleteEquipment(EquipmentProto.DeleteEquipmentRequest request){
        return equipmentServiceFutureStub.deleteEquipment(request);
    }

    public ListenableFuture<EquipmentProto.Equipment> getEquipment(Integer equipmentId){
        return equipmentServiceFutureStub.getEquipment(EquipmentProto.GetEquipmentRequest.newBuilder().setEquipmentId(equipmentId).build());
    }
}
