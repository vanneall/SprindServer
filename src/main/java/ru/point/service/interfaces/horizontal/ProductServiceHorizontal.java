package ru.point.service.interfaces.horizontal;

import lombok.NonNull;
import ru.point.entity.table.Product;

import java.util.List;

public interface ProductServiceHorizontal {
    Product getProductById(@NonNull Long id);
}
