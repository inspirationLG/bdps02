package com.bdps.mservice.userorginfo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;
import com.bdps.equipment.EquipmentProto;
import javax.persistence.*;

@Data
@Entity
@ToString(callSuper = true)
@Table(name = "bdps_equipment")
@ProtoClass(EquipmentProto.Equipment.class)
@NoArgsConstructor
public class BdpsEquipment extends BaseModel{
    @Id
    @ProtoField
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Integer equipmentId;

    @ProtoField
    @Column(name = "staff_id")
    private Integer staffId;

    @ProtoField
    @Column(name = "type")
    private String type;

    @ProtoField
    @Column(name = "imei")
    private String imei;

    @ProtoField
    @Column(name = "name")
    private String name;
}
