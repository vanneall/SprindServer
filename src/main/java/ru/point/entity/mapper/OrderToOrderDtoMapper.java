package ru.point.entity.mapper;

import org.springframework.stereotype.Component;
import ru.point.entity.dto.OrderDto;
import ru.point.entity.table.Order;

import java.util.function.Function;

@Component
public class OrderToOrderDtoMapper implements Function<Order, OrderDto> {
    @Override
    public OrderDto apply(Order order) {
        return new OrderDto(
            order.getId(),
            order.getDeliveryCost(),
            order.getProductsCost(),
            order.getSummaryCost()
        );
    }
}
