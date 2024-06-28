package ru.point.utils.factory.interfaces;

import ru.point.entity.table.User;

public interface UserFactory {

    User create(
        final String username,
        final String password,
        final String name,
        final String surname,
        final String secret,
        final String email
    );

}
