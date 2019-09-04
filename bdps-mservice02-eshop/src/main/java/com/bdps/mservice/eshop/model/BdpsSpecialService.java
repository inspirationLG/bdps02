package com.bdps.mservice.eshop.model;

import com.bdps.special_service.SpecialServiceProto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@ProtoClass(SpecialServiceProto.SpecialService.class)
@Table(name = "bdps_special_service")
public class BdpsSpecialService extends  BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_service_id")
    @ProtoField
    private Integer specialServiceId;

    @Column(name = "organization_id")
    @ProtoField
    private Integer organizationId;

    @Column(name = "name")
    @ProtoField
    private String name;

    @Column(name = "time")
    @ProtoField
    private LocalDateTime time;

    @Column(name = "specialservice_creator")
    @ProtoField
    private String specialserviceCreator;

    @Column(name = "coverage_rate")
    @ProtoField
    private BigDecimal coverageRate;

    @Column(name = "remain_time")
    @ProtoField
    private LocalDateTime remainTime;

    @Column(name = "state")
    @ProtoField
    private String state;

    @Column(name = "address")
    @ProtoField
    private String address;

}
