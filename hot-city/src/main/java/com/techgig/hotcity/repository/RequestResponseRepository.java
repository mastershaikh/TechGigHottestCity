package com.techgig.hotcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techgig.hotcity.entity.RequestResponseEntity;

@Repository
public interface RequestResponseRepository extends JpaRepository<RequestResponseEntity, Long> {

}
