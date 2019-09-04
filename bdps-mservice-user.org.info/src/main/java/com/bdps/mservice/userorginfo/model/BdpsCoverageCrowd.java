package com.bdps.mservice.userorginfo.model;

import com.bdps.coverage_crowd.CoverageCrowdProto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;

import javax.persistence.*;

@Data
@Entity
@ToString(callSuper = true)
@Table(name = "bdps_coverage_crowd")
@ProtoClass(CoverageCrowdProto.CoverageCrowd.class)
@NoArgsConstructor
public class BdpsCoverageCrowd extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ProtoField
    @Column(name = "coverage_crowd_id")
    private Integer coverageCrowdId;

    @ProtoField
    @Column(name = "special_service_id")
    private Integer specialServiceId;

    @ProtoField
    @Column(name = "total_number")
    private Integer totalNumber;

    @ProtoField
    @Column(name = "beneficiary_type")
    private String beneficiaryType;

    @ProtoField
    @Column(name = "beneficiary_age")
    private Integer beneficiaryAge;
}
