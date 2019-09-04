package com.bdps.gateway.resolvers.comment;

import com.bdps.comment.CommentProto;
import com.bdps.gateway.clients.CommentClient;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CommentQuery implements GraphQLQueryResolver {
    private final CommentClient commentClient;

    public  ListenableFuture<CommentProto.Comments> listComments(CommentProto.ListCommentRequest request){
        return commentClient.listComments(request);
    }

    public ListenableFuture<CommentProto.Comment> getComment(CommentProto.GetCommentRequest request){
        return commentClient.getComment(request.getCommentId());
    }
}
