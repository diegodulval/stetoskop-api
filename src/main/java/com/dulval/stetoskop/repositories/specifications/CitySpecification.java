/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.repositories.specifications;

import com.dulval.stetoskop.domain.City;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public final class CitySpecification {

    public static Specification whereState(Integer param) {
        return (Specification<City>) (Root<City> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> cb.equal(root.join("state").get("id"), param);
    }

}
