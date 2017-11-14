/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.repositories.specifications;

import com.dulval.stetoskop.domain.Pacient;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public final class PrescriptionSpecification {

    public static Specification<Pacient> byDate(Date param) {
        return (Root<Pacient> root, CriteriaQuery<?> query, CriteriaBuilder builder)
                -> builder.equal(root.get("date"), param);
    }

    public static Specification whereDoctor(Integer param) {
        return (Specification<Pacient>) (Root<Pacient> root, CriteriaQuery<?> query, CriteriaBuilder cb)
                -> cb.equal(root.join("pacient").join("doctor").get("id"), param);
    }

    public static Specification wherePacient(Integer param) {
        return (Specification<Pacient>) (Root<Pacient> root, CriteriaQuery<?> query, CriteriaBuilder cb)
                -> cb.equal(root.join("pacient").get("id"), param);
    }

}
