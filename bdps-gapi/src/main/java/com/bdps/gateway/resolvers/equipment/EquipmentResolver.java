package com.bdps.gateway.resolvers.equipment;

import com.bdps.equipment.EquipmentProto;
import com.bdps.gateway.clients.StaffClient;
import com.bdps.staff.StaffProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.util.concurrent.ListenableFuture;

@Component
public class EquipmentResolver implements GraphQLResolver <EquipmentProto.Equipment> {
    @Autowired
    private StaffClient staffClient;

    public ListenableFuture <StaffProto.Staff> staff(EquipmentProto.Equipment equipment) {
        return staffClient.getStaff(equipment.getStaffId());
    }
}
