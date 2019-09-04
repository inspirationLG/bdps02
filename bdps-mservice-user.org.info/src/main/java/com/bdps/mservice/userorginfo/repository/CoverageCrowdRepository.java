package com.bdps.mservice.userorginfo.repository;

import com.bdps.mservice.userorginfo.model.BdpsCoverageCrowd;
import com.bdps.mservice.userorginfo.model.BdpsOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zcz
 * @CreateTime 2019/8/28 9:47
 */
public interface CoverageCrowdRepository extends JpaRepository<BdpsCoverageCrowd, Integer> {
}