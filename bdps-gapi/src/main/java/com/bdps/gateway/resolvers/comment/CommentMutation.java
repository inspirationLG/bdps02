package com.bdps.gateway.resolvers.comment;

import com.bdps.comment.CommentProto;
import com.bdps.gateway.clients.CommentClient;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class CommentMutation implements GraphQLMutationResolver {
    @Autowired
    private final CommentClient commentClient;

    public ListenableFuture<CommentProto.Comment> addComment(CommentProto.AddCommentRequest request) {
        return commentClient.addComment(request);
    }

    public ListenableFuture<CommentProto.Comment> updateComment(CommentProto.UpdateCommentRequest request) {
        return commentClient.updateComment(request);
    }

    public ListenableFuture<CommentProto.Comment> deleteComment(CommentProto.DeleteCommentRequest request) {
        return commentClient.deleteComment(request);
    }

}
