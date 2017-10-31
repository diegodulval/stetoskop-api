package com.dulval.stetoskop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulval.stetoskop.domain.City;

@Repository
public interface CidadeRepository extends JpaRepository<City, Integer> {

}
