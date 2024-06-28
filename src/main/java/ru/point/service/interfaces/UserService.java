package ru.point.service.interfaces;

import ru.point.entity.dto.*;
import ru.point.entity.table.Address;
import ru.point.entity.table.User;

import java.util.List;

public interface UserService {
    List<OrderDto> getOrdersByUsername(String username);

    List<ReviewDto> getReviewsByUsername(String username);

    UserDto getUserInfoByUsername(String username);

    void setAddress(Address address, String username);

    User getUserByUsername(final String username);

}
