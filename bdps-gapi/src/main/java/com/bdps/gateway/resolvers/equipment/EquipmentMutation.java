package com.bdps.gateway.resolvers.equipment;

import com.bdps.equipment.EquipmentProto;
import com.bdps.gateway.clients.EquipmentClient;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EquipmentMutation implements GraphQLMutationResolver {
    @Autowired
    private final EquipmentClient equipmentClient;

    public ListenableFuture<EquipmentProto.Equipment> addEquipment(EquipmentProto.AddEquipmentRequest request){
        return equipmentClient.addEquipment(request);
    }

    public ListenableFuture<EquipmentProto.Equipment> updateEquipment(EquipmentProto.UpdateEquipmentRequest request){
        return equipmentClient.updateEquipment(request);
    }

    public ListenableFuture<EquipmentProto.Equipment> deleteEquipment(EquipmentProto.DeleteEquipmentRequest request){
        return equipmentClient.deleteEquipment(request);
    }

}
