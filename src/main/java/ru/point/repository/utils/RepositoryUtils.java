package ru.point.repository.utils;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class RepositoryUtils {
    public static <T> TypedQuery<T> setPagingToQuery(TypedQuery<T> query, int offset, int limit) {
        return query.setFirstResult(offset).setMaxResults(limit);
    }
}
