package ru.point.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.point.entity.table.Category;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryById(Long id);
}
