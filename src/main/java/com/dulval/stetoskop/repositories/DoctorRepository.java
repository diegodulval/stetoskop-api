package com.dulval.stetoskop.repositories;

import com.dulval.stetoskop.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dulval.stetoskop.domain.User;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Transactional(readOnly = true)
    User findByEmail(String email);

}
