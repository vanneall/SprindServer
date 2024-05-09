package ru.point.service.implementations;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.TokenDto;
import ru.point.entity.dto.UserDto;
import ru.point.entity.exception.UserAlreadyExistException;
import ru.point.entity.exception.UserCredentialsInvalidException;
import ru.point.entity.table.Authority;
import ru.point.entity.table.Cart;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.AuthorityRepository;
import ru.point.repository.interfaces.UsersRepository;
import ru.point.security.token.TokenGenerator;
import ru.point.service.interfaces.UserService;

import javax.security.auth.login.CredentialException;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@Service
public class UsersServiceImpl implements UserDetailsService, UserService {

    private final UsersRepository usersRepository;

    private final AuthorityRepository authorityRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final TokenGenerator tokenGenerator;

    @Override
    public void save(UserDto userDto) {

        boolean isUserExist = false;
        try {
            isUserExist = loadUserByUsername(userDto.telephone()) != null;
        } catch (UsernameNotFoundException ignored) {
        }

        if (isUserExist) throw new UserAlreadyExistException();

        User newUser = new User();
        newUser.setUsername(userDto.telephone());
        newUser.setPassword(passwordEncoder.encode(userDto.password()));
        newUser.setSecret(passwordEncoder.encode(userDto.secret()));
        newUser.setName(userDto.name());
        newUser.setSecondName(userDto.secondName());
        newUser.setEmail(userDto.email());

        Cart cart = new Cart();
        cart.setProducts(Collections.emptySet());
        newUser.setCart(cart);

        Authority authority = authorityRepository.findAuthorityByName("ROLE_USER");
        newUser.setAuthorities(Set.of(authority));

        usersRepository.save(newUser);
    }

    @Override
    public TokenDto generateToken(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);

        var actualEncodedPassword = userDetails.getPassword();
        if (!passwordEncoder.matches(password, actualEncodedPassword)) {
            throw new UserCredentialsInvalidException("Wrong username or password");
        }

        return new TokenDto(tokenGenerator.apply(userDetails));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findUserByUsername(username);

        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    @Override
    @Transactional
    public void reset(String username, String secret, String newPassword) {
        User user = (User) loadUserByUsername(username);

        if (passwordEncoder.matches(secret, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
        } else {
            throw new UserCredentialsInvalidException("Wrong secret");
        }
    }
}
