package com.bdps.mservice.eshop.repository;

import com.bdps.mservice.eshop.model.BdpsOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<BdpsOrder, Integer> {
}
