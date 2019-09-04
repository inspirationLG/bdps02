package com.bdps.mservice.userorginfo.repository;

import com.bdps.mservice.userorginfo.model.BdpsCoverageCrowdOfItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zcz
 * @CreateTime 2019/8/29 14:22
 */
public interface CoverageCrowdOfItemRepository extends JpaRepository<BdpsCoverageCrowdOfItem,Integer> {
}