package ru.point.utils.factory.interfaces;

import ru.point.entity.table.Order;
import ru.point.entity.table.Product;

import java.util.Set;

public interface OrderFactory {
    Order create(Set<Product> productsInOrder);
}
