package com.bdps.mservice.eshop.repository;

import com.bdps.mservice.eshop.model.BdpsSpecialServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zcz
 * @CreateTime 2019/8/29 16:30
 */
public interface SpecialServiceItemRepository extends JpaRepository<BdpsSpecialServiceItem,Integer> {
}