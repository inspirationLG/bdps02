package com.bdps.mservice.userorginfo.model;

import com.bdps.activity_staff.ActivityStaffProto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bdps_activity_staff")
@NoArgsConstructor
@ProtoClass(ActivityStaffProto.ActivityStaff.class)
public class BdpsActivityStaff extends BaseModel{
    @Id
    @ProtoField
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_staff_id")
    private Integer activityStaffId;

    @ProtoField
    @Column(name = "staff_id")
    private Integer staffId;

    @ProtoField
    @Column(name = "activity_id")
    private Integer activityId;
}
