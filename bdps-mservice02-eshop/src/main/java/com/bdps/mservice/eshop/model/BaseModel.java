package com.bdps.mservice.eshop.model;

/**
 * @author zcz
 * @CreateTime 2019/8/29 16:25
 */
import com.bdps.mservice.eshop.converter.LocalDateTimeConverterImpl;
import com.bdps.mservice.eshop.converter.ProtobufNullValueInspectorImpl;
import io.grpc.Status;
import lombok.Data;
import net.badata.protobuf.converter.annotation.ProtoField;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;


@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseModel {

    @Column(name = "description")
    @ProtoField
    private String description;

    @Column(name = "creator")
    @CreatedBy
    @ProtoField
    private String creator;

    @Column(name = "create_time")
    @ProtoField(nullValue = ProtobufNullValueInspectorImpl.class,converter = LocalDateTimeConverterImpl.class)
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "access_group")
    private Integer accessGroup;

    @Column(name = "amender")
    @LastModifiedBy
    @ProtoField
    private String amender;

    @Column(name = "amend_time")
    @LastModifiedDate
    @ProtoField(nullValue = ProtobufNullValueInspectorImpl.class,converter = LocalDateTimeConverterImpl.class)
    private LocalDateTime amendTime;

    @Column(name = "record_version")
    @Version
    private Integer recordVersion;

    @Column(name = "flag_deleted")
    private Boolean flagDeleted;
}

