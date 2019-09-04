package com.bdps.gateway.resolvers.comment;

import com.bdps.comment.CommentProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CommentsResolver implements GraphQLResolver<CommentProto.Comments> {
    public List<CommentProto.Comment> nodes(CommentProto.Comments comments) {
        return comments.getNodesList();
    }
}
