package com.dulval.stetoskop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulval.stetoskop.domain.Medicament;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Integer> {

}
