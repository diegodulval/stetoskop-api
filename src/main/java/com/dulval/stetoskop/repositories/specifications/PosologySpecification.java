/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.repositories.specifications;

import com.dulval.stetoskop.domain.Pacient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public final class PosologySpecification {

    public static Specification<Pacient> byDescription(String desc) {
        return (Root<Pacient> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> builder.like(builder.lower(root.<String>get("description")),
                "%" + desc.trim().toLowerCase() + "%");
    }

    public static Specification whereDoctor(Integer param) {
        return (Specification<Pacient>) (Root<Pacient> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> cb.equal(root.join("doctor").get("id"), param);
    }

}
