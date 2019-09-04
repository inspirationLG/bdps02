package com.bdps.mservice.eshop.repository;

import com.bdps.mservice.eshop.model.BdpsOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<BdpsOrderDetail, Integer> {
}
