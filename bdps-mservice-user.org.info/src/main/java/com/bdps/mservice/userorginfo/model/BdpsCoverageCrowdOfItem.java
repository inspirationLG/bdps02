package com.bdps.mservice.userorginfo.model;

import com.bdps.coverage_crowd_of_item.CoverageCrowdOfItemProto;
import lombok.Data;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;

import javax.persistence.*;

@Data
@Entity
@ToString(callSuper = true)
@Table(name = "bdps_coverage_crowd_of_item")
@ProtoClass(CoverageCrowdOfItemProto.CoverageCrowdOfItem.class)
public class BdpsCoverageCrowdOfItem extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coverage_crowd_of_item_id")
    @ProtoField
    private Integer coverageCrowdOfItemId;

    @ProtoField
    @Column(name = "special_service_detail_id")
    private Integer specialServiceDetailId;

    @ProtoField
    @Column(name = "coverage_crowd_id")
    private Integer coverageCrowdId;
}
