package ru.point.service.interfaces;

import ru.point.entity.dto.RegisteredUserDto;
import ru.point.entity.dto.TokenDto;

public interface UserSecurityService {
    void createNewUser(RegisteredUserDto registeredUserDto);

    TokenDto generateAuthorizationTokenForUser(String username, String password);

    void resetUserPassword(String username, String secret, String newPassword);
}
