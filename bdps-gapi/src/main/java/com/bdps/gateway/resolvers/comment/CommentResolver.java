package com.bdps.gateway.resolvers.comment;

import com.bdps.comment.CommentProto;
import com.bdps.gateway.clients.CommentClient;
import com.bdps.gateway.clients.StaffClient;
import com.bdps.staff.StaffProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentResolver implements GraphQLResolver <CommentProto.Comment>{
//OrderDetail还没实现
//    @Autowired
//    private StaffClient staffClient;
//
//    public ListenableFuture<StaffProto.Staff> orderDetail(CommentProto.Comment comment){
//        return staffClient.getStaff(comment.getOrderDetailId());
//
//    }
}
