package com.bdps.gateway.resolvers.staff;

import com.bdps.staff.StaffProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class StaffsResolver implements GraphQLResolver <StaffProto.Staffs> {
    public List<StaffProto.Staff> nodes(StaffProto.Staffs staffs){
        return staffs.getNodesList();
    }
}

