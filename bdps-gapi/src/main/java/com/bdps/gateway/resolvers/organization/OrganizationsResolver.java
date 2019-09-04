package com.bdps.gateway.resolvers.organization;

import com.bdps.gateway.clients.OrganizationClient;
import com.bdps.organization.OrganizationProto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Hchien Ying
 * @date:2019/8/20
 * @description:
 */
@Component
public class OrganizationsResolver implements GraphQLResolver<OrganizationProto.Organizations> {

    public List<OrganizationProto.Organization> nodes(OrganizationProto.Organizations organizations) {
        return organizations.getNodesList();
    }
}
