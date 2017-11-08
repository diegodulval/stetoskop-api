/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.repositories;

import com.dulval.stetoskop.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego Dulval
 */
@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

}
