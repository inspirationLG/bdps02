package com.bdps.gateway.resolvers.equipment;

import com.bdps.equipment.EquipmentProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EquipmentsResolver implements GraphQLResolver <EquipmentProto.Equipments> {
    public List<EquipmentProto.Equipment> nodes(EquipmentProto.Equipments equipments){
        return equipments.getNodesList();
    }
}
