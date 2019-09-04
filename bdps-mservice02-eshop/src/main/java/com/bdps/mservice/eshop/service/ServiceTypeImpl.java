package com.bdps.mservice.eshop.service;



import com.bdps.service_type.ServiceTypeProto;
import com.bdps.service_type.ServiceTypeServiceGrpc;
import com.bdps.mservice.eshop.model.BdpsServiceType;
import com.bdps.mservice.eshop.repository.ServiceTypeRepository;
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
import io.grpc.Status;
import java.util.Optional;

@AllArgsConstructor
@GrpcService
public class ServiceTypeImpl extends ServiceTypeServiceGrpc.ServiceTypeServiceImplBase {
    @Autowired
    private final ServiceTypeRepository serviceTypeRepository;

    @Transactional
    @Override
    public void addServiceType(ServiceTypeProto.AddServiceTypeRequest request, StreamObserver<ServiceTypeProto.ServiceType> responseObserver) {
        Configuration configuration = Configuration.builder()
                .addIgnoredFields(new FieldsIgnore().add(BdpsServiceType.class, "servcieTypeId"))
                .build();
        BdpsServiceType bdpsServiceType = Converter.create(configuration).toDomain(BdpsServiceType.class, request);
        EntityUtil.fill(bdpsServiceType);
        BdpsServiceType newBdpsServiceType= serviceTypeRepository.save(bdpsServiceType);
        responseObserver.onNext(modelToRpc(newBdpsServiceType));
        responseObserver.onCompleted();
        return;
    }

    @Transactional
    @Override
    public void updateServiceType(ServiceTypeProto.UpdateServiceTypeRequest request, StreamObserver<ServiceTypeProto.ServiceType> responseObserver) {
        if (serviceTypeRepository.existsById(request.getServiceTypeId())) {
            BdpsServiceType bdpsServiceType = Converter.create().toDomain(BdpsServiceType.class, request);
            BdpsServiceType oldBdpsServiceType = serviceTypeRepository.getOne(bdpsServiceType.getServiceTypeId());
            CopyUtils.copyProperties(bdpsServiceType, oldBdpsServiceType, true);
            EntityUtil.fill(oldBdpsServiceType);
            BdpsServiceType newBdpsService = serviceTypeRepository.save(oldBdpsServiceType);
            responseObserver.onNext(modelToRpc(newBdpsService));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所要更新的Service不存在！").asRuntimeException();

    }

    @Transactional
    @Override
    public void deleteServiceType(ServiceTypeProto.DeleteServiceTypeRequest request, StreamObserver<ServiceTypeProto.ServiceType> responseObserver) {
        if (request.getServiceTypeId() > 0 ){
            Optional<BdpsServiceType> bdpsServiceType = serviceTypeRepository.findById(request.getServiceTypeId());
            if (bdpsServiceType.isPresent()){
                serviceTypeRepository.delete(bdpsServiceType.get());
                responseObserver.onNext(modelToRpc(bdpsServiceType.get()));
                responseObserver.onCompleted();
                return;
            }
        }
        throw Status.NOT_FOUND.withDescription("要删除的ServiceType不存在！").asRuntimeException();
    }

    @Override
    public void getServiceType(ServiceTypeProto.GetServiceTypeRequest request, StreamObserver<ServiceTypeProto.ServiceType> responseObserver) {
        Optional<BdpsServiceType> bdpsServiceType = serviceTypeRepository.findById(request.getServiceTypeId());
        if (bdpsServiceType.isPresent()) {
            responseObserver.onNext(modelToRpc(bdpsServiceType.get()));
            responseObserver.onCompleted();
            return;
        }
        throw Status.NOT_FOUND.withDescription("所查找的ServiceTypeId为" + request.getServiceTypeId() + ")不存在！").asRuntimeException();
    }

    @Override
    public void listServiceType(ServiceTypeProto.ListServiceTypeRequest request, StreamObserver<ServiceTypeProto.ServiceTypes> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<BdpsServiceType> page = serviceTypeRepository.findAll(pageable);
        responseObserver.onNext(ServiceTypeProto.ServiceTypes.newBuilder()
                .addAllNodes(page.map(this::modelToRpc).getContent())
                .setCount((int) page.getTotalElements())
                .setPage(request.getPage())
                .setLimit(request.getLimit())
                .build());
        responseObserver.onCompleted();
    }

    private ServiceTypeProto.ServiceType modelToRpc (BdpsServiceType bdpsServiceType){
        return Converter.create().toProtobuf(ServiceTypeProto.ServiceType.class, bdpsServiceType);
    }
}
