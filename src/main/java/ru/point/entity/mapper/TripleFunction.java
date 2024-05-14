package ru.point.entity.mapper;

public interface TripleFunction<T, U1, U2, R> {
    R apply(T t, U1 u1, U2 u2);
}
