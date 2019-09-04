package com.bdps.mservice.eshop.model;

import com.bdps.comment.CommentProto;
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
@Table(name = "bdps_order")
public class BdpsOrder extends BaseModel{
    @Id
    @ProtoField
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @ProtoField
    @Column(name = "staff_id")
    private Integer staffId;

    @ProtoField
    @Column(name = "organization_id")
    private Integer organizationId;

    @ProtoField
    @Column(name = "special_service_item_id")
    private Integer specialServiceItemId;

    @ProtoField
    @Column(name = "order_create_time")
    private String orderCreateTime;

    @ProtoField
    @Column(name = "amount")
//    private BigDecimal amount;
    private Integer amount;

}
