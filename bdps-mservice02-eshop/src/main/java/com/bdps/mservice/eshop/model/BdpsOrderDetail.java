package com.bdps.mservice.eshop.model;

import com.bdps.order.OrderProto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@ToString(callSuper = true)
@ProtoClass(OrderProto.Order.class)
@NoArgsConstructor
@Table(name = "bdps_order_detail")
public class BdpsOrderDetail extends BaseModel{
    @Id
    @ProtoField
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Integer orderDetailId;
    @ProtoField
    @Column(name = "order_id")
    private Integer orderId;
    @ProtoField
    @Column(name = "amount")
    private Integer amount;


}
