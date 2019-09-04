package com.bdps.mservice.eshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;
import com.bdps.service_type.ServiceTypeProto;
import javax.persistence.*;

@Data
@Entity
@ToString(callSuper = true)
@Table(name = "bdps_service_type")
@ProtoClass(ServiceTypeProto.ServiceType.class)
@NoArgsConstructor
public class BdpsServiceType extends BaseModel{
    @Id
    @ProtoField
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_type_id")
    private Integer serviceTypeId;

    @Column(name = "type")
    @ProtoField
    private Integer type;

    @Column(name = "name")
    @ProtoField
    private String name;
}
