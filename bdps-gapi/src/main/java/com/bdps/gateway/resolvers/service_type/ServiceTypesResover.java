package com.bdps.gateway.resolvers.service_type;


import com.bdps.service_type.ServiceTypeProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ServiceTypesResover implements GraphQLResolver<ServiceTypeProto.ServiceTypes> {
    public List<ServiceTypeProto.ServiceType> nodes(ServiceTypeProto.ServiceTypes serviceTypes) {
        return serviceTypes.getNodesList();
    }
}