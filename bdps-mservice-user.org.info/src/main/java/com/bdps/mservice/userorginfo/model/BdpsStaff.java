package com.bdps.mservice.userorginfo.model;

import com.bdps.staff.StaffProto;
import lombok.Data;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@ToString(callSuper = true)
@Table(name = "bdps_staff")
@ProtoClass(StaffProto.Staff.class)
public class BdpsStaff extends BaseModel{
    @Id
    @ProtoField
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer staffId;

    @ProtoField
    @Column(name = "id_card")
    private String idCard;

    @ProtoField
    @Column(name = "staff_type_id")
    private Integer staffTypeId;

    @ProtoField
    @Column(name = "organization_id")
    private Integer organizationId;

    @ProtoField
    @Column(name = "region_id")
    private Integer regionId;

    @ProtoField
    @Column(name = "parent_staff_id")
    private Integer parentStaffId;

    @ProtoField
    @Column(name = "relationship_name")
    private String relationshipName;

    @ProtoField
    @Column(name = "level")
    private Integer level;

    @ProtoField
    @Column(name = "name")
    private String name;

    @ProtoField
    @Column(name = "role")
    private String role;

    @ProtoField
    @Column(name = "tel_1")
    private String tel1;

    @ProtoField
    @Column(name = "tel_2")
    private String tel2;

    @ProtoField
    @Column(name = "email")
    private String email;

    @ProtoField
    @Column(name = "address")
    private String address;

    @ProtoField
    @Column(name = "login_account")
    private String loginAccount;

    @ProtoField
    @Column(name = "password")
    private String password;

    @ProtoField
    @Column(name = "img")
    private String img;

    @ProtoField
    @Column(name = "join_time")
    @CreatedDate
    private LocalDateTime joinTime;

    @ProtoField
    @Column(name = "sex")
    private Boolean sex;

    @ProtoField
    @Column(name = "age")
    private Integer age;

    @ProtoField
    @Column(name = "couple_name")
    private String coupleName;

    @ProtoField
    @Column(name = "vip_expire_time")
    private LocalDateTime vipExpireTime;

    @ProtoField
    @Column(name = "nation")
    private String nation;

    @ProtoField
    @Column(name = "politics_status")
    private String politicsStatus;

    @ProtoField
    @Column(name = "education_level")
    private String educationLevel;

    @ProtoField
    @Column(name = "medical_method")
    private String medicalMethod;

    @ProtoField
    @Column(name = "height")
    private Double height;

    @ProtoField
    @Column(name = "weight")
    private Double weight;

    @ProtoField
    @Column(name = "remark")
    private String remark;

    @ProtoField
    @Column(name = "permanent_residence_address")
    private String permanentResidenceAddress;

    @ProtoField
    @Column(name = "marital_status")
    private String maritalStatus;

    @ProtoField
    @Column(name = "residence_status")
    private String residenceStatus;

    @ProtoField
    @Column(name = "financial_resource")
    private String financialResource;

    @ProtoField
    @Column(name = "hobby")
    private String hobby;

    @ProtoField
    @Column(name = "body_status")
    private String bodyStatus;

    @ProtoField
    @Column(name = "received_total")
    private Integer receivedTotal;

    @ProtoField
    @Column(name = "specialservice_donetotal")
    private Integer specialserviceDonetotal;
}
