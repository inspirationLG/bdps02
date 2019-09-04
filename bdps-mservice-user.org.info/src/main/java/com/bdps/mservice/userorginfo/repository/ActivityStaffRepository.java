package com.bdps.mservice.userorginfo.repository;

import com.bdps.mservice.userorginfo.model.BdpsActivityStaff;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:Hchien Ying
 * @date:2019/8/30
 * @description:
 */
public interface ActivityStaffRepository extends JpaRepository<BdpsActivityStaff, Integer> {
}
