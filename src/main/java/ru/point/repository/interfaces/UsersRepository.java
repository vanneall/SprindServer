package ru.point.repository.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.point.entity.table.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
