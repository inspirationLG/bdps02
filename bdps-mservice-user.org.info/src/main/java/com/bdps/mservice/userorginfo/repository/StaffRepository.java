package com.bdps.mservice.userorginfo.repository;

import com.bdps.mservice.userorginfo.model.BdpsStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<BdpsStaff, Integer> {

}
