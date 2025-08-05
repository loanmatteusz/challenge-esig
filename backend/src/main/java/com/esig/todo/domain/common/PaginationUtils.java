package com.esig.todo.domain.common;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public class PaginationUtils {

    public static <T, R> PaginatedResponse<R> toPaginatedResponse(Page<T> page, Function<T, R> mapper) {
        List<R> content = page.getContent().stream()
                .map(mapper)
                .toList();

        return new PaginatedResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast());
    }
}
