package com.bdps.gateway.resolvers.staff_type;

import com.bdps.staff_type.StaffTypeProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StaffTypeResolver implements GraphQLResolver<StaffTypeProto.StaffTypes> {
    public List<StaffTypeProto.StaffType> nodes(StaffTypeProto.StaffTypes staff_types){
        return staff_types.getNodesList();
    }
}
