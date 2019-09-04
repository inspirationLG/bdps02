package com.bdps.mservice.userorginfo.model;

import com.bdps.activity.ActivityProto;
import com.bdps.staff_type.StaffTypeProto;
import lombok.Data;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@ToString(callSuper = true)
@Table(name = "bdps_activity")
@ProtoClass(ActivityProto.Activity.class)
public class BdpsActivity extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ProtoField
    @Column(name = "activity_id")
    private Integer activityId;
    @ProtoField
    @Column(name = "organization_id")
    private Integer organizationId;
    @ProtoField
    @Column(name = "activity_creator")
    private String activityCreator;
    @ProtoField
    @Column(name = "activity_create_time")
    private  String activityCreateTime;
    @ProtoField
    @Column(name = "theme")
    private String theme;
    @ProtoField
    @Column(name = "staff")
    private String staff;
    @ProtoField
    @Column(name = "site")
    private String site;
    @ProtoField
    @Column(name = "content")
    private String content;
    @ProtoField
    @Column(name = "state")
    private String state;
    @ProtoField
    @Column(name = "credit")
    private Integer credit;
    @ProtoField
    @Column(name = "remark")
    private String remark;
}
