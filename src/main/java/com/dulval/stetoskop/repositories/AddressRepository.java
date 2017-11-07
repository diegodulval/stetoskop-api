package com.dulval.stetoskop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulval.stetoskop.domain.Address;

@Repository
public interface EnderecoRepository extends JpaRepository<Address, Integer> {

}
