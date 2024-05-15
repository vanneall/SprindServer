package ru.point.entity.mapper;

import org.springframework.stereotype.Component;
import ru.point.entity.dto.UserDto;
import ru.point.entity.table.User;

import java.util.function.Function;

@Component
public class UserToUserDtoMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(
            user.getUsername(),
            user.getName(),
            user.getSecondName(),
            user.getEmail(),
            user.getAddress()
        );
    }
}
