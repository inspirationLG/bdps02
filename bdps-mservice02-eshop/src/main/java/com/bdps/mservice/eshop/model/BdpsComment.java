package com.bdps.mservice.eshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;
import com.bdps.comment.CommentProto;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@ToString(callSuper = true)
@Table(name = "bdps_comment")
@ProtoClass(CommentProto.Comment.class)
@NoArgsConstructor
public class BdpsComment extends BaseModel{
    @Id
    @ProtoField
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;

    @ProtoField
    @Column(name = "order_detail_id")
    private Integer orderDetailId;

    @ProtoField
    @Column(name = "star")
    private Integer star;

    @ProtoField
    @Column(name = "content")
    private String content;

    @ProtoField
    @Column(name = "createdAt")
    @CreatedDate
    private LocalDateTime createdAt;
}
