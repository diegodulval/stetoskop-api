package com.dulval.stetoskop.repositories;

import com.dulval.stetoskop.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dulval.stetoskop.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>, JpaSpecificationExecutor {

    @Transactional(readOnly = true)
    User findByEmail(String email);

}
