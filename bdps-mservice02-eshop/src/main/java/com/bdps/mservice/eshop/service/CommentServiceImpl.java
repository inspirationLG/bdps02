package com.bdps.mservice.eshop.service;

import com.bdps.comment.CommentProto;
import com.bdps.comment.CommentServiceGrpc;
import com.bdps.mservice.eshop.model.BdpsComment;
import com.bdps.mservice.eshop.repository.CommentRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import net.badata.protobuf.converter.Configuration;
import net.badata.protobuf.converter.Converter;
import net.badata.protobuf.converter.FieldsIgnore;
import com.bdps.mservice.eshop.util.CopyUtils;
import com.bdps.mservice.eshop.util.EntityUtil;
import java.util.Optional;

@AllArgsConstructor
@GrpcService
public class CommentServiceImpl extends CommentServiceGrpc.CommentServiceImplBase {

    @Autowired
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public void addComment(CommentProto.AddCommentRequest request, StreamObserver<CommentProto.Comment> responseObserver) {
        Configuration configuration = Configuration.builder()
                .addIgnoredFields(new FieldsIgnore().add(BdpsComment.class, "commentId"))
                .build();
        BdpsComment bdpsComment = Converter.create(configuration).toDomain(BdpsComment.class, request);
        EntityUtil.fill(bdpsComment);
        BdpsComment newBdpsComment = commentRepository.save(bdpsComment);
        responseObserver.onNext(modelToRpc(newBdpsComment));
        responseObserver.onCompleted();
        return;
    }

    @Override
    public void getComment(CommentProto.GetCommentRequest request, StreamObserver<CommentProto.Comment> responseObserver) {
        Optional<BdpsComment> bdpsComment = commentRepository.findById(request.getCommentId());
        if (bdpsComment.isPresent()){
            responseObserver.onNext(modelToRpc(bdpsComment.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所查找的CommentId为" + request.getCommentId() + ")不存在！").asRuntimeException();
    }

    @Override
    public void listComment(CommentProto.ListCommentRequest request, StreamObserver<CommentProto.Comments> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsComment> page = commentRepository.findAll(pageable);
        responseObserver.onNext(CommentProto.Comments.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void updateComment(CommentProto.UpdateCommentRequest request, StreamObserver<CommentProto.Comment> responseObserver) {
        if (commentRepository.existsById(request.getCommentId())) {
            BdpsComment bdpsComment = Converter.create().toDomain(BdpsComment.class, request);
            BdpsComment oldBdpsComment = commentRepository.getOne(bdpsComment.getCommentId());
            CopyUtils.copyProperties(bdpsComment, oldBdpsComment, true);
            EntityUtil.fill(oldBdpsComment);
            BdpsComment newBdpsComment = commentRepository.save(oldBdpsComment);
            responseObserver.onNext(modelToRpc(newBdpsComment));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的Comment不存在！").asRuntimeException();
    }

    @Transactional
    @Override
    public void deleteComment(CommentProto.DeleteCommentRequest request, StreamObserver<CommentProto.Comment> responseObserver) {
        if (request.getCommentId() > 0 ){
            Optional<BdpsComment> bdpsComment = commentRepository.findById(request.getCommentId());
            if (bdpsComment.isPresent()){
                commentRepository.delete(bdpsComment.get());
                responseObserver.onNext(modelToRpc(bdpsComment.get()));
                responseObserver.onCompleted();
                return;
            }
        }
        throw Status.NOT_FOUND.withDescription("要删除的Comment不存在！").asRuntimeException();

    }

    private CommentProto.Comment modelToRpc(BdpsComment bdpsComment) {
        return Converter.create().toProtobuf(CommentProto.Comment.class, bdpsComment);
    }
}
