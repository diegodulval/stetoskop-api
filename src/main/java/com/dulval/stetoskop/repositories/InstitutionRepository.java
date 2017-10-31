package com.dulval.stetoskop.repositories;

import com.dulval.stetoskop.domain.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dulval.stetoskop.domain.User;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

    @Transactional(readOnly = true)
    User findByEmail(String email);

}
