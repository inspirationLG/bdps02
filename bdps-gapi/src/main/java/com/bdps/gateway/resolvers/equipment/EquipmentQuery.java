package com.bdps.gateway.resolvers.equipment;

import com.bdps.gateway.clients.EquipmentClient;
import com.bdps.equipment.EquipmentProto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EquipmentQuery implements GraphQLQueryResolver {
    private final EquipmentClient equipmentClient;

    public ListenableFuture<EquipmentProto.Equipment> getEquipment(EquipmentProto.GetEquipmentRequest request) {
        return equipmentClient.getEquipment(request.getEquipmentId());
    }

    public ListenableFuture<EquipmentProto.Equipments> listEquipment(EquipmentProto.ListEquipmentRequest request){
        return equipmentClient.listEquipment(request);
    }
}
