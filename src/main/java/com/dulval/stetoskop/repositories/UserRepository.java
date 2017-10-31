package com.dulval.stetoskop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dulval.stetoskop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional(readOnly = true)
    User findByEmail(String email);

//    @Transactional(readOnly = true)
//    @Query("SELECT DISTINCT obj FROM User obj WHERE obj.name LIKE %:name% AND obj.email LIKE %:email%")
//    Page<User> findAll(String name, String email, Pageable pageRequest);

}
