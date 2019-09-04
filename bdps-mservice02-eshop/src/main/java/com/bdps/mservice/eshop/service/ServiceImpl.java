package com.bdps.mservice.eshop.service;



import com.bdps.service.ServiceProto;
import com.bdps.service.ServiceServiceGrpc;
import com.bdps.mservice.eshop.model.BdpsService;
import com.bdps.mservice.eshop.repository.ServiceRepository;
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
public class ServiceImpl extends ServiceServiceGrpc.ServiceServiceImplBase {
    @Autowired
    private final ServiceRepository serviceRepository;

    @Transactional
    @Override
    public void addService(ServiceProto.AddServiceRequest request, StreamObserver<ServiceProto.Service> responseObserver) {
        Configuration configuration = Configuration.builder()
                .addIgnoredFields(new FieldsIgnore().add(BdpsService.class, "serviceId"))
                .build();
        BdpsService bdpsService = Converter.create(configuration).toDomain(BdpsService.class, request);
        EntityUtil.fill(bdpsService);
        BdpsService newBdpsService = serviceRepository.save(bdpsService);
        responseObserver.onNext(modelToRpc(newBdpsService));
        responseObserver.onCompleted();
        return;
    }

    @Transactional
    @Override
    public void updateService(ServiceProto.UpdateServiceRequest request, StreamObserver<ServiceProto.Service> responseObserver) {
        if (serviceRepository.existsById(request.getServiceId())) {
            BdpsService bdpsService = Converter.create().toDomain(BdpsService.class, request);
            BdpsService oldBdpsService = serviceRepository.getOne(bdpsService.getServiceId());
            CopyUtils.copyProperties(bdpsService, oldBdpsService, true);
            EntityUtil.fill(oldBdpsService);
            BdpsService newBdpsService = serviceRepository.save(oldBdpsService);
            responseObserver.onNext(modelToRpc(newBdpsService));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的Service不存在！").asRuntimeException();
    }

    @Transactional
    @Override
    public void deleteService(ServiceProto.DeleteServiceRequest request, StreamObserver<ServiceProto.Service> responseObserver) {
        if (request.getServiceId() > 0 ){
            Optional<BdpsService> bdpsService = serviceRepository.findById(request.getServiceId());
            if (bdpsService.isPresent()){
                serviceRepository.delete(bdpsService.get());
                responseObserver.onNext(modelToRpc(bdpsService.get()));
                responseObserver.onCompleted();
                return;
            }
        }
        throw Status.NOT_FOUND.withDescription("要删除的Service不存在！").asRuntimeException();
    }

    @Override
    public void getService(ServiceProto.GetServiceRequest request, StreamObserver<ServiceProto.Service> responseObserver) {
        Optional<BdpsService> bdpsService = serviceRepository.findById(request.getServiceId());
        if (bdpsService.isPresent()) {
            responseObserver.onNext(modelToRpc(bdpsService.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所查找的ServiceId为" + request.getServiceId() + ")不存在！").asRuntimeException();
    }

    @Override
    public void listService(ServiceProto.ListServiceRequest request, StreamObserver<ServiceProto.Services> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsService> page = serviceRepository.findAll(pageable);
        responseObserver.onNext(ServiceProto.Services.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
        return;
    }

    private ServiceProto.Service modelToRpc(BdpsService bdpsService) {
        return Converter.create().toProtobuf(ServiceProto.Service.class, bdpsService);
    }
}
