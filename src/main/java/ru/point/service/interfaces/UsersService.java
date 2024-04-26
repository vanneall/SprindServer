package ru.point.service.interfaces;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.UserDto;
import ru.point.entity.table.Authority;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.AuthorityRepository;
import ru.point.repository.interfaces.UsersRepository;

import java.util.Set;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    public void save(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());

        Authority authority = authorityRepository.findAuthorityByName("ROLE_USER");

        user.setAuthorities(Set.of(authority));

        usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findUserByUsername(username);

        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }
}
