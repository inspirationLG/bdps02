package com.bdps.mservice.userorginfo.model;

import com.bdps.organization.OrganizationProto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bdps_organization")
@ProtoClass(OrganizationProto.Organization.class)
public class BdpsOrganization extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    @ProtoField
    private Integer organizationId;

    @Column(name = "region_id")
    @ProtoField
    private Integer regionId;

    @ProtoField
    @Column(name = "parent_organization_id")
    private Integer parentOrganizationId;

    @Column(name = "name")
    @ProtoField
    private String name;

    @Column(name = "manager")
    @ProtoField
    private String manager;

    @Column(name = "manager_tel")
    @ProtoField
    private String managerTel;

    @Column(name = "operation_area")
    @ProtoField
    private String operationArea;

    @Column(name = "operation_method")
    @ProtoField
    private String operationMethod;

    @Column(name = "address")
    @ProtoField
    private String address;

    @Column(name = "street_no")
    @ProtoField
    private String streetNo;

    @Column(name = "type")
    @ProtoField
    private Integer type;

/*    public BdpsOrganization(Integer regionId, Integer parentOrganizationId, String name, String manager, String managerTel, String operationArea, String operationMethod, String address, String streetNo, Integer flag) {
        this.regionId = regionId;
        this.parentOrganizationId = parentOrganizationId;
        this.name = name;
        this.manager = manager;
        this.managerTel = managerTel;
        this.operationArea = operationArea;
        this.operationMethod = operationMethod;
        this.address = address;
        this.streetNo = streetNo;
        this.flag = flag;
    }*/
}
