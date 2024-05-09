package ru.point.service.interfaces;

import ru.point.entity.dto.TokenDto;
import ru.point.entity.dto.UserDto;
import ru.point.entity.exception.UserAlreadyExistException;

public interface UserService {
    void save(UserDto userDto);

    TokenDto generateToken(String username, String password);

    void reset(String username, String secret, String newPassword);
}
