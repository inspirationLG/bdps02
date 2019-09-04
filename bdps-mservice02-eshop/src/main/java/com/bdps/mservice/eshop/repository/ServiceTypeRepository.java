package com.bdps.mservice.eshop.repository;


import com.bdps.mservice.eshop.model.BdpsServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository  extends JpaRepository<BdpsServiceType,Integer> {
}
