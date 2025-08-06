package com.esig.todo.domain.task;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.esig.todo.domain.task.dtos.TaskFilterDTO;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecifications {

    public static Specification<Task> withFilters(TaskFilterDTO filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.id() != null) {
                predicates.add(cb.equal(root.get("id"), Long.valueOf(filters.id())));
            }

            if (filters.query() != null && !filters.query().isBlank()) {
                String likeSearch = "%" + filters.query().toLowerCase() + "%";
                Predicate titleLike = cb.like(cb.lower(root.get("title")), likeSearch);
                Predicate descriptionLike = cb.like(cb.lower(root.get("description")), likeSearch);
                predicates.add(cb.or(titleLike, descriptionLike));
            }

            if (filters.responsibleId() != null && !filters.responsibleId().isBlank()) {
                predicates.add(cb.equal(root.get("responsibleId"), filters.responsibleId()));
            }

            if (filters.status() != null) {
                predicates.add(cb.equal(root.get("status"), filters.status()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
