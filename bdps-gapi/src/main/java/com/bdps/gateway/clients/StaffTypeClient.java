package com.bdps.gateway.clients;


import com.bdps.region.RegionProto;
import com.bdps.staff_type.StaffTypeProto;
import com.bdps.staff_type.StaffTypeServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StaffTypeClient {
    @GrpcClient("user-org-info-grpc-server")
    private StaffTypeServiceGrpc.StaffTypeServiceFutureStub staffTypeServiceFutureStub;

    public ListenableFuture<StaffTypeProto.StaffType> addStaffType(StaffTypeProto.AddStaffTypeRequest request) {
        return staffTypeServiceFutureStub.addStaffType(request);
    }

    public ListenableFuture<StaffTypeProto.StaffType> updateStaffType(StaffTypeProto.UpdateStaffTypeRequest request) {
        return staffTypeServiceFutureStub.updateStaffType(request);
    }

    public ListenableFuture<StaffTypeProto.StaffType> getStaffType(Integer id) {
        return staffTypeServiceFutureStub.getStaffType(StaffTypeProto.GetStaffTypeRequest.newBuilder().setStaffTypeId(id).build());
    }

    public ListenableFuture<StaffTypeProto.StaffType> deleteStaffType(StaffTypeProto.DeleteStaffTypeRequest request) {
        return staffTypeServiceFutureStub.deleteStaffType(request);
    }

    public ListenableFuture<StaffTypeProto.StaffTypes> listStaffType(StaffTypeProto.ListStaffTypeRequest request) {
        return staffTypeServiceFutureStub.listStaffType(request);
    }
}
