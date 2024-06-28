package ru.point.utils.factory.implementations;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.point.entity.table.Authority;
import ru.point.entity.table.Cart;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.AuthorityRepository;
import ru.point.utils.factory.interfaces.UserFactory;

import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@Component
public class UserFactoryImpl implements UserFactory {

    private final BCryptPasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    @Override
    public User create(
        final String username,
        final String password,
        final String name,
        final String surname,
        final String secret,
        final String email
    ) {
        User newUser = new User();

        newUser.setUsername(username);
        newUser.setName(name);
        newUser.setSecondName(surname);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setSecret(passwordEncoder.encode(secret));

        Cart cart = new Cart();
        cart.setProducts(Collections.emptySet());
        newUser.setCart(cart);

        Authority authority = authorityRepository.findAuthorityByName("ROLE_USER");
        newUser.setAuthorities(Set.of(authority));

        return newUser;
    }
}
