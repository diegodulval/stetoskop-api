package com.dulval.stetoskop.repositories;

import com.dulval.stetoskop.domain.Unity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnityRepository extends JpaRepository<Unity, Integer> {

}
