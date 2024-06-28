package ru.point.service.implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.point.entity.dto.RegisteredUserDto;
import ru.point.entity.dto.TokenDto;
import ru.point.entity.exception.UserAlreadyExistException;
import ru.point.entity.exception.UserCredentialsInvalidException;
import ru.point.entity.table.User;
import ru.point.security.token.AuthorizationTokenFactory;
import ru.point.service.interfaces.UserSecurityService;
import ru.point.service.interfaces.horizontal.UserServiceHorizontal;
import ru.point.utils.factory.interfaces.UserFactory;

@AllArgsConstructor
@Component
public class UserSecurityServiceImpl implements UserSecurityService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthorizationTokenFactory authorizationTokenFactory;
    private final UserFactory userFactory;
    private final UserServiceHorizontal userServiceHorizontal;

    @Override
    public void createNewUser(RegisteredUserDto registeredUserDto) {
        try {
            checkIsUserExist(registeredUserDto.telephone());
            throw new UserAlreadyExistException();
        } catch (UsernameNotFoundException ignored) {}

        User newUser = userFactory.create(
            registeredUserDto.telephone(),
            registeredUserDto.password(),
            registeredUserDto.name(),
            registeredUserDto.secondName(),
            registeredUserDto.secret(),
            registeredUserDto.email()
        );

        userServiceHorizontal.saveNewUser(newUser);
    }

    private boolean checkIsUserExist(final String username) {
        return userServiceHorizontal.getUserByUsername(username) != null;
    }

    @Override
    public TokenDto generateAuthorizationTokenForUser(String username, String password) {
        UserDetails userDetails = userServiceHorizontal.getUserByUsername(username);

        var actualEncodedPassword = userDetails.getPassword();
        if (!passwordEncoder.matches(password, actualEncodedPassword)) {
            throw new UserCredentialsInvalidException("Wrong username or password");
        }

        return new TokenDto(authorizationTokenFactory.apply(userDetails));
    }


    @Override
    @Transactional
    public void resetUserPassword(String username, String secret, String newPassword) {
        User user = userServiceHorizontal.getUserByUsername(username);

        if (passwordEncoder.matches(secret, user.getSecret())) {
            user.setPassword(passwordEncoder.encode(newPassword));
        } else {
            throw new UserCredentialsInvalidException("Wrong secret");
        }
    }
}
