package com.bdps.mservice.userorginfo.model;

import com.bdps.region.RegionProto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;

import javax.persistence.*;

@Data
@Entity
@ToString(callSuper = true)
@Table(name = "bdps_region")
@ProtoClass(RegionProto.Region.class)
@NoArgsConstructor
public class BdpsRegion extends BaseModel {
    @Id
    @ProtoField
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer regionId;

    @ProtoField
    @Column(name = "name")
    private String name;

    @ProtoField
    @Column(name = "short_name")
    private String shortName;

    @ProtoField
    @Column(name = "code")
    private String code;

    @ProtoField
    @Column(name = "parent_region_id")
    private Integer parentRegionId;

    @ProtoField
    @Column(name = "level")
    private Integer level;
}
