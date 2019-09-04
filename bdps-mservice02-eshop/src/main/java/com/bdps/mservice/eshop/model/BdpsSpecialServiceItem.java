package com.bdps.mservice.eshop.model;

import com.bdps.special_service_item.SpecialServiceItemProto;
import lombok.Data;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bdps_special_service_item")
@ProtoClass(SpecialServiceItemProto.SpecialServiceItem.class)
@ToString(callSuper = true)
public class BdpsSpecialServiceItem extends  BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_service_item_id")
    @ProtoField
    private Integer specialServiceItemId;

    @ProtoField
    @Column(name = "staff_id")
    private Integer staffId;

    @ProtoField
    @Column(name = "status")
    private String status;

    @ProtoField
    @Column(name = "unit")
    private String unit;

    @ProtoField
    @Column(name = "amount_or_times")
    private String amountOrTimes;

    @ProtoField
    @Column(name = "used_amount_or_times")
    private String usedAmountOrTimes;
}
