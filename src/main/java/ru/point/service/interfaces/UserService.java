package ru.point.service.interfaces;

import ru.point.entity.dto.*;
import ru.point.entity.table.Address;

import java.util.List;

public interface UserService {
    void save(RegisteredUserDto registeredUserDto);

    TokenDto generateToken(String username, String password);

    void reset(String username, String secret, String newPassword);

    List<OrderDto> getOrdersByUsername(String username);

    List<ReviewDto> getReviewsByUsername(String username);

    UserDto getUserInfoByUsername(String username);

    void setAddress(Address address, String username);
}
