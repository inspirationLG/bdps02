package com.bdps.mservice.eshop.repository;

import com.bdps.mservice.eshop.model.BdpsService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<BdpsService,Integer> {

}
