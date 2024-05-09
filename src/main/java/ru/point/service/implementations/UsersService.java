package ru.point.service.implementations;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.UserDto;
import ru.point.entity.table.Authority;
import ru.point.entity.table.Cart;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.AuthorityRepository;
import ru.point.repository.interfaces.UsersRepository;

import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@Service
public class UsersService implements UserDetailsService {

    UsersRepository usersRepository;

    AuthorityRepository authorityRepository;

    public void save(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());

        Cart cart = new Cart();
        cart.setProducts(Collections.emptySet());
        user.setCart(cart);

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
