package com.dulval.stetoskop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dulval.stetoskop.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
