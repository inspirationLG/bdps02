package com.bdps.gateway.clients;

import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import com.bdps.staff.StaffProto;
import com.bdps.staff.StaffServiceGrpc;


@Service
public class StaffClient {
    //异步
    @GrpcClient("user-org-info-grpc-server")
    private StaffServiceGrpc.StaffServiceFutureStub staffServiceFutureStub;

    public ListenableFuture<StaffProto.Staff> addStaff(StaffProto.AddStaffRequest request){
        return staffServiceFutureStub.addStaff(request);
    }

//    public ListenableFuture<StaffProto.Staff> getStaff(StaffProto.GetStaffRequest request){
//        return staffServiceFutureStub.getStaff(request);
//    }

    public ListenableFuture<StaffProto.Staff> getStaff(Integer staffId){
        return staffServiceFutureStub.getStaff(StaffProto.GetStaffRequest.newBuilder().setStaffId(staffId).build());
    }

    public ListenableFuture<StaffProto.Staffs> listStaff(StaffProto.ListStaffRequest request){
        return staffServiceFutureStub.listStaff(request);
    }

    public ListenableFuture<StaffProto.Staff> updateStaff(StaffProto.UpdateStaffRequest request){
        return staffServiceFutureStub.updateStaff(request);
    }

    public ListenableFuture <StaffProto.Staff> deleteStaff(StaffProto.DeleteStaffRequest request){
        return staffServiceFutureStub.deleteStaff(request);
    }

}
