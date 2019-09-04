package com.bdps.mservice.userorginfo.model;

import com.bdps.staff_type.StaffTypeProto;
import lombok.Data;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;

import javax.persistence.*;
@Data
@Entity
@ToString(callSuper = true)
@Table(name = "bdps_staff_type")
@ProtoClass(StaffTypeProto.StaffType.class)
public class BdpsStaffType extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_type_id")
    @ProtoField
    private Integer staffTypeId;

    @Column(name = "type")
    @ProtoField
    private Integer type;

    @Column(name = "name")
    @ProtoField
    private String name;
}
