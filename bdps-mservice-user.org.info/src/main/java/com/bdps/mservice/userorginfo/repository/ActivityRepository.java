package com.bdps.mservice.userorginfo.repository;

import com.bdps.mservice.userorginfo.model.BdpsActivity;
import com.bdps.mservice.userorginfo.model.BdpsStaffType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<BdpsActivity, Integer> {
//    Page<BdpsActivity> findAllByIsDeletedFalse(Pageable pageable);
}
