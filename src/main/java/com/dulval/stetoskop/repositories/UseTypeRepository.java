package com.dulval.stetoskop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulval.stetoskop.domain.UseType;

@Repository
public interface UseTypeRepository extends JpaRepository<UseType, Integer> {

}
