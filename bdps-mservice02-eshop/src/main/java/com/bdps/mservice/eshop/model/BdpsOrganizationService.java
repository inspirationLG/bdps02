package com.bdps.mservice.eshop.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bdps_organization_service")
public class BdpsOrganizationService {
    @Id
    @Column(name = "organization_service_id")
    private Long organizationServiceId;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "description")
    private String description;

    @Column(name = "creator")
    private String creator;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "access_group")
    private Long accessGroup;

    @Column(name = "amender")
    private String amender;

    @Column(name = "amender_time")
    private LocalDateTime amenderTime;

    @Column(name = "record_version")
    private Long recordVersion;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
