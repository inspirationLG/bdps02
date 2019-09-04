package com.bdps.gateway.clients;


import com.bdps.comment.CommentProto;
import com.bdps.comment.CommentServiceGrpc;
import com.bdps.service.ServiceProto;
import com.bdps.service.ServiceServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class CommentClient {
    @GrpcClient("eshop-grpc-server")
    private CommentServiceGrpc.CommentServiceFutureStub commentServiceFutureStub;

    public ListenableFuture<CommentProto.Comment> addComment(CommentProto.AddCommentRequest request) {
        return commentServiceFutureStub.addComment(request);
    }

    public ListenableFuture<CommentProto.Comment> updateComment(CommentProto.UpdateCommentRequest request) {
        return commentServiceFutureStub.updateComment(request);
    }

    public ListenableFuture<CommentProto.Comments> listComments(CommentProto.ListCommentRequest request) {
        return commentServiceFutureStub.listComment(request);
    }

    public ListenableFuture<CommentProto.Comment> deleteComment(CommentProto.DeleteCommentRequest request) {
        return commentServiceFutureStub.deleteComment(request);
    }

    public ListenableFuture<CommentProto.Comment> getComment(Integer commentId) {
        return commentServiceFutureStub.getComment(CommentProto.GetCommentRequest.newBuilder().setCommentId(commentId).build());
    }

}
