package com.bdps.mservice.userorginfo.service;

import com.bdps.mservice.userorginfo.model.BdpsRegion;
import com.bdps.mservice.userorginfo.repository.RegionRepository;
import com.bdps.mservice.userorginfo.util.CopyUtils;
import com.bdps.mservice.userorginfo.util.EntityUtil;
import com.bdps.region.RegionProto;
import com.bdps.region.RegionServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.badata.protobuf.converter.Configuration;
import net.badata.protobuf.converter.Converter;
import net.badata.protobuf.converter.FieldsIgnore;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author zcz
 * @CreateTime 2019/8/14 13:35
 */

/**
 * 返回的类型不一样  Regions   Region
 * set和get的成员变量不一样
 */
@AllArgsConstructor
@GrpcService
public class RegionServiceImpl extends RegionServiceGrpc.RegionServiceImplBase {

    @Autowired
    private final RegionRepository regionRepository;
    @Transactional
    @Override
    public void addRegion(RegionProto.AddRegionRequest request, StreamObserver<RegionProto.Region> responseObserver) {
        int parentRegionId = request.getParentRegionId();
        if (parentRegionId == 0 || regionRepository.existsById(parentRegionId)) {
            Configuration configuration = Configuration.builder()
                    .addIgnoredFields(new FieldsIgnore().add(BdpsRegion.class, "regionId"))
                    .build();
            BdpsRegion bdpsRegion = Converter.create(configuration).toDomain(BdpsRegion.class, request);
            EntityUtil.fill(bdpsRegion);
            BdpsRegion newBdpsRegion = regionRepository.save(bdpsRegion);
            responseObserver.onNext(modelToRpc(newBdpsRegion));
            responseObserver.onCompleted();
        } else throw Status.NOT_FOUND.withDescription("parentRegion不存在！").asRuntimeException();
    }
    @Override
    public void listRegion(RegionProto.ListRegionRequest request, StreamObserver<RegionProto.Regions> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsRegion> page = regionRepository.findAll(pageable);
        responseObserver.onNext(RegionProto.Regions.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }
    @Override
    public void getRegion(RegionProto.GetRegionRequest request, StreamObserver<RegionProto.Region> responseObserver) {
        Optional<BdpsRegion> bdpsRegion = regionRepository.findById(request.getId());
        if (bdpsRegion.isPresent()){
            responseObserver.onNext(modelToRpc(bdpsRegion.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所查找的Region(Id为" + request.getId() + ")不存在！").asRuntimeException();

    }

    @Transactional
    @Override
    public void updateRegion(RegionProto.UpdateRegionRequest request, StreamObserver<RegionProto.Region> responseObserver) {
        //check(request);
        if (regionRepository.existsById(request.getRegionId())) {
            BdpsRegion bdpsRegion = Converter.create().toDomain(BdpsRegion.class, request);
            BdpsRegion oldBdpsRegion = regionRepository.getOne(bdpsRegion.getRegionId());
            CopyUtils.copyProperties(bdpsRegion, oldBdpsRegion, true);
            EntityUtil.fill(oldBdpsRegion);
            BdpsRegion newBdpsRegion = regionRepository.save(oldBdpsRegion);
            responseObserver.onNext(modelToRpc(newBdpsRegion));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的Region不存在！").asRuntimeException();
    }

    private RegionProto.Region modelToRpc(BdpsRegion bdpsRegion) {
        return Converter.create().toProtobuf(RegionProto.Region.class, bdpsRegion);
    }
}
