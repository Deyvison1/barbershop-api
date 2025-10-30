package com.api.barbershop.repository.specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.api.barbershop.dto.HaircutFilterDTO;
import com.api.barbershop.model.Haircut;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class HaircutSpecification {

	public static Specification<Haircut> filterBy(HaircutFilterDTO filter) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (filter != null) {
				addPredicateIfPresent(predicates, hasName(filter.getName()), root, cb);
				addPredicateIfPresent(predicates, hasDescription(filter.getDescription()), root, cb);
				addPredicateIfPresent(predicates, hasPrice(filter.getPrice()), root, cb);
				addPredicateIfPresent(predicates, hasTime(filter.getTime()), root, cb);
				addPredicateIfPresent(predicates, hasCreatedDate(filter.getCreatedDate()), root, cb);
			}

			query.distinct(true);
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

	private static void addPredicateIfPresent(List<Predicate> predicates, Specification<Haircut> spec,
			Root<Haircut> root, CriteriaBuilder cb) {
		if (spec != null) {
			Predicate predicate = spec.toPredicate(root, null, cb);
			if (predicate != null) {
				predicates.add(predicate);
			}
		}
	}

	public static Specification<Haircut> hasName(String name) {
		return (root, _, cb) -> (name == null || name.isEmpty()) ? null
				: cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
	}

	public static Specification<Haircut> hasDescription(String description) {
		return (root, _, cb) -> (description == null || description.isEmpty()) ? null
				: cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
	}

	public static Specification<Haircut> hasPrice(BigDecimal price) {
		return (root, _, cb) -> (price == null) ? null : cb.equal(root.get("price"), price);
	}

	public static Specification<Haircut> hasTime(Integer time) {
		return (root, _, cb) -> (time == null) ? null : cb.equal(root.get("time"), time);
	}

	public static Specification<Haircut> hasCreatedDate(LocalDateTime createdDate) {
		return (root, _, cb) -> {
			if (createdDate == null)
				return null;

			LocalDateTime startOfDay = createdDate.toLocalDate().atStartOfDay(); // 00:00:00
			LocalDateTime endOfDay = createdDate.toLocalDate().atTime(LocalTime.MAX); // 23:59:59.999999999

			return cb.between(root.get("createdDate"), startOfDay, endOfDay);
		};
	}
}
