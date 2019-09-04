package com.bdps.gateway.resolvers.staff;

import com.bdps.gateway.clients.OrganizationClient;
import com.bdps.gateway.clients.RegionClient;
import com.bdps.gateway.clients.StaffClient;
import com.bdps.gateway.clients.StaffTypeClient;
import com.bdps.organization.OrganizationProto;
import com.bdps.region.RegionProto;
import com.bdps.staff.StaffProto;
import com.bdps.staff_type.StaffTypeProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaffResolver implements GraphQLResolver <StaffProto.Staff> {

    @Autowired
    private StaffClient staffClient;
    @Autowired
    private RegionClient regionClient;
    @Autowired
    private OrganizationClient organizationClient;
    @Autowired
    private StaffTypeClient staffTypeClient;

    public ListenableFuture <StaffProto.Staff> parentStaff(StaffProto.Staff staff) {
        return staffClient.getStaff(staff.getParentStaffId());
    }

    public ListenableFuture <RegionProto.Region> region(StaffProto.Staff staff) {
        return regionClient.getRegion(staff.getRegionId());
    }

    //Organization的proto没有getOrgnization方法
    public ListenableFuture <OrganizationProto.Organization> organization(StaffProto.Staff staff){
        return organizationClient.getOrganization(staff.getOrganizationId());
    }

    public ListenableFuture <StaffTypeProto.StaffType> staffType(StaffProto.Staff staff){
        return staffTypeClient.getStaffType(staff.getStaffTypeId());
    }

//    还没确定上一级编号如何返回
//    public StaffProto.Staff parentStaff(StaffProto.Staff staff){
//        return staff.getDefaultInstanceForType();
//    }

}

