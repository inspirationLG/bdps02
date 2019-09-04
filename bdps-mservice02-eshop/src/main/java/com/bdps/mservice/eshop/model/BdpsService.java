package com.bdps.mservice.eshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;
import com.bdps.service.ServiceProto;
import javax.persistence.*;


@Data
@Entity
@ToString(callSuper = true)
@Table(name = "bdps_service")
@ProtoClass(ServiceProto.Service.class)
@NoArgsConstructor
public class BdpsService extends BaseModel{
    @Id
    @ProtoField
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @ProtoField
    @Column(name = "service_type_id")
    private Integer serviceTypeId;

    @ProtoField
    @Column(name = "type")
    private Integer type;

    @ProtoField
    @Column(name = "name")
    private String name;

    @ProtoField
    @Column(name = "content")
    private String content;

    @ProtoField
    @Column(name = "unit")
    private String unit;

    @ProtoField
    @Column(name = "unit_price")
    private Double unitPrice;
}
