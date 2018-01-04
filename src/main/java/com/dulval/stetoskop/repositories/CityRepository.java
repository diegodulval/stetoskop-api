package com.dulval.stetoskop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulval.stetoskop.domain.City;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>, JpaSpecificationExecutor {

}
