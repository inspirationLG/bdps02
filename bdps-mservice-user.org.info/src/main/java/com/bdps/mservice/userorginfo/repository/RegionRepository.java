package com.bdps.mservice.userorginfo.repository;

import com.bdps.mservice.userorginfo.model.BdpsRegion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zcz
 * @CreateTime 2019/8/14 13:32
 */
public interface RegionRepository extends JpaRepository<BdpsRegion,Integer> {
}
