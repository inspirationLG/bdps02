package com.bdps.mservice.userorginfo.service;

import com.bdps.coverage_crowd.CoverageCrowdProto;
import com.bdps.coverage_crowd.CoverageCrowdServiceGrpc;
import com.bdps.mservice.userorginfo.model.BdpsCoverageCrowd;
import com.bdps.mservice.userorginfo.repository.CoverageCrowdRepository;
import com.bdps.mservice.userorginfo.util.CopyUtils;
import com.bdps.mservice.userorginfo.util.EntityUtil;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.badata.protobuf.converter.Converter;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author zcz
 * @CreateTime 2019/8/28 9:48
 */
@AllArgsConstructor
@GrpcService
public class CoverageCrowdImpl extends CoverageCrowdServiceGrpc.CoverageCrowdServiceImplBase {
    @Autowired
    private final CoverageCrowdRepository coverageCrowdRepository;

    @Override
    public void getCoverageCrowd(CoverageCrowdProto.GetCoverageCrowdRequest request, StreamObserver<CoverageCrowdProto.CoverageCrowd> responseObserver) {
        Optional<BdpsCoverageCrowd> bdpsCoverageCrowd = coverageCrowdRepository.findById(request.getCoverageCrowdId());
        if (bdpsCoverageCrowd.isPresent()) {
            responseObserver.onNext(modelToRpc(bdpsCoverageCrowd.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所查找的CoverageCrowd不存在！").asRuntimeException();
    }

    @Override
    public void listCoverageCrowd(CoverageCrowdProto.ListCoverageCrowdRequest request, StreamObserver<CoverageCrowdProto.CoverageCrowds> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsCoverageCrowd> page = coverageCrowdRepository.findAll(pageable);
        responseObserver.onNext(CoverageCrowdProto.CoverageCrowds.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void addCoverageCrowd(CoverageCrowdProto.AddCoverageCrowdRequest request, StreamObserver<CoverageCrowdProto.CoverageCrowd> responseObserver) {
        BdpsCoverageCrowd bdpsCoverageCrowd = Converter.create().toDomain(BdpsCoverageCrowd.class, request);
        EntityUtil.fill(bdpsCoverageCrowd);
        BdpsCoverageCrowd newBdpsCoverageCrowd = coverageCrowdRepository.save(bdpsCoverageCrowd);
        responseObserver.onNext(modelToRpc(newBdpsCoverageCrowd));
        responseObserver.onCompleted();
        return;
    }

    @Transactional
    @Override
    public void updateCoverageCrowd(CoverageCrowdProto.UpdateCoverageCrowdRequest request, StreamObserver<CoverageCrowdProto.CoverageCrowd> responseObserver) {
        Optional<BdpsCoverageCrowd> optionalBdpsCoverageCrowd = coverageCrowdRepository.findById(request.getCoverageCrowdId());
        if (optionalBdpsCoverageCrowd.isPresent()) {
            BdpsCoverageCrowd coverageCrowd = optionalBdpsCoverageCrowd.get();
            BdpsCoverageCrowd bdpsCoverageCrowd = Converter.create().toDomain(BdpsCoverageCrowd.class, request);
            CopyUtils.copyProperties(bdpsCoverageCrowd, coverageCrowd, true);
            BdpsCoverageCrowd newBdpsCoverageCrowd = coverageCrowdRepository.save(coverageCrowd);
            responseObserver.onNext(modelToRpc(newBdpsCoverageCrowd));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的CoverageCrowd不存在！").asRuntimeException();
    }

    @Transactional
    @Override
    public void deleteCoverageCrowd(CoverageCrowdProto.DeleteCoverageCrowdRequest request, StreamObserver<CoverageCrowdProto.CoverageCrowd> responseObserver) {
        Optional<BdpsCoverageCrowd> optionalBdpsCoverageCrowd = coverageCrowdRepository.findById(request.getCoverageCrowdId());
        if (optionalBdpsCoverageCrowd.isPresent()) {
            BdpsCoverageCrowd bdpsCoverageCrowd = optionalBdpsCoverageCrowd.get();
            coverageCrowdRepository.deleteById(bdpsCoverageCrowd.getCoverageCrowdId());
            responseObserver.onNext(modelToRpc(bdpsCoverageCrowd));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要删除的CoverageCrowd不存在！").asRuntimeException();

    }


    private CoverageCrowdProto.CoverageCrowd modelToRpc(BdpsCoverageCrowd bdpsCoverageCrowd) {
        return Converter.create().toProtobuf(CoverageCrowdProto.CoverageCrowd.class, bdpsCoverageCrowd);
    }
}
