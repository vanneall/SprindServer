package ru.point.utils.factory.implementations;

import org.springframework.stereotype.Component;
import ru.point.entity.table.Order;
import ru.point.entity.table.Product;
import ru.point.utils.factory.interfaces.OrderFactory;

import java.util.Set;

@Component
public class OrderFactoryImpl implements OrderFactory {

    @Override
    public Order create(Set<Product> productsInOrder) {
        Order order = new Order();
        order.setProducts(productsInOrder);
        order.setDeliveryCost(0.0);

        double productsCostAmount = productsInOrder
            .stream()
            .mapToDouble(product -> product.getPrice().getMoney())
            .sum();
        order.setProductsCost(productsCostAmount);

        return order;
    }
}
