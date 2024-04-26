package ru.point.repository.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.point.entity.table.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority findAuthorityByName(String name);
}
