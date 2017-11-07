package com.dulval.stetoskop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulval.stetoskop.domain.Pacient;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, Integer> {

}
