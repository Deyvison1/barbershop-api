package com.api.barbershop.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.api.barbershop.dto.barber.BarberFilterDTO;
import com.api.barbershop.model.Barber;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BarberSpecification {
	public static Specification<Barber> filterBy(BarberFilterDTO filter) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (filter != null) {
				addPredicateIfPresent(predicates, hasName(filter.getName()), root, cb);
				addPredicateIfPresent(predicates, hasSpecialtyName(filter.getSpecialtyName()), root, cb);
			}

			query.distinct(true);
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

	private static void addPredicateIfPresent(List<Predicate> predicates, Specification<Barber> spec, Root<Barber> root,
			CriteriaBuilder cb) {
		if (spec != null) {
			Predicate predicate = spec.toPredicate(root, null, cb);
			if (predicate != null) {
				predicates.add(predicate);
			}
		}
	}

	public static Specification<Barber> hasName(String name) {
		return (root, _, cb) -> (name == null || name.isEmpty()) ? null
				: cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
	}

	public static Specification<Barber> hasSpecialtyName(String name) {
	    return (root, query, cb) -> {
	        if (name == null || name.isEmpty()) return null;

	        if (query != null) {
	            query.distinct(true);
	        }

	        return cb.like(
	            cb.lower(root.join("specialties", JoinType.LEFT).get("name")),
	            "%" + name.toLowerCase() + "%"
	        );
	    };
	}
}
