package com.bdps.mservice.eshop.repository;

import com.bdps.mservice.eshop.model.BdpsComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<BdpsComment, Integer> {

}
