package com.dulval.stetoskop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulval.stetoskop.domain.Posology;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface PosologyRepository extends JpaRepository<Posology, Integer>, JpaSpecificationExecutor {

}
