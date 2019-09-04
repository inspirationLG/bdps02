package com.bdps.gateway.resolvers.special_service_item;

import com.bdps.gateway.clients.SpecialServiceItemClient;
import com.bdps.gateway.clients.StaffClient;
import com.bdps.special_service_item.SpecialServiceItemProto;
import com.bdps.staff.StaffProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zcz
 * @CreateTime 2019/8/29 15:39
 */
@Component
public class SpecialServiceItemResolver implements GraphQLResolver<SpecialServiceItemProto.SpecialServiceItem> {

    @Autowired
    private StaffClient staffClient;
    public ListenableFuture<StaffProto.Staff> staff (SpecialServiceItemProto.SpecialServiceItem specialServiceItem){
        return staffClient.getStaff(specialServiceItem.getStaffId());
    }
}