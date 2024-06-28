package ru.point.service.interfaces.horizontal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.point.entity.table.User;

public interface UserServiceHorizontal {
    User getUserByUsername(String username);

    void saveNewUser(User user);

}
