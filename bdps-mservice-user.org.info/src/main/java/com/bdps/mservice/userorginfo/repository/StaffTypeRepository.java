package com.bdps.mservice.userorginfo.repository;


import com.bdps.mservice.userorginfo.model.BdpsStaffType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffTypeRepository extends JpaRepository<BdpsStaffType, Integer> {
    Page<BdpsStaffType> findAllByFlagDeletedFalse(Pageable pageable);

}
