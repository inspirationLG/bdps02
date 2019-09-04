package com.bdps.mservice.eshop.model;

import com.bdps.special_service_fund.SpecialServiceFundProto;
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
@ProtoClass(SpecialServiceFundProto.SpecialServiceFund.class)
@Table(name = "bdps_special_service_fund")
public class BdpsSpecialServiceFund extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_service_fund_id")
    @ProtoField
    private Integer specialServiceFundId;

    @Column(name = "coverage_crowd_id")
    @ProtoField
    private Integer coverageCrowdId;

    @Column(name = "staff_id")
    @ProtoField
    private Integer staffId;

    @Column(name = "amount")
    @ProtoField
    private Float amount;

    @Column(name = "balance")
    @ProtoField
    private Float balance;


}
