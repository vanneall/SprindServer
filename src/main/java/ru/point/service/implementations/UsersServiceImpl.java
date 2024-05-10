package ru.point.service.implementations;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.*;
import ru.point.entity.exception.UserAlreadyExistException;
import ru.point.entity.exception.UserCredentialsInvalidException;
import ru.point.entity.mapper.OrderToOrderDtoMapper;
import ru.point.entity.mapper.ReviewToReviewDtoMapper;
import ru.point.entity.mapper.UserToUserDtoMapper;
import ru.point.entity.table.Authority;
import ru.point.entity.table.Cart;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.AuthorityRepository;
import ru.point.repository.interfaces.UsersRepository;
import ru.point.security.token.TokenGenerator;
import ru.point.service.interfaces.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class UsersServiceImpl implements UserDetailsService, UserService {

    private final UsersRepository usersRepository;

    private final AuthorityRepository authorityRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final TokenGenerator tokenGenerator;

    private final UserToUserDtoMapper userDtoMapper;

    private final ReviewToReviewDtoMapper reviewDtoMapper;

    private final OrderToOrderDtoMapper orderDtoMapper;

    @Override
    public void save(RegisteredUserDto registeredUserDto) {

        boolean isUserExist = false;
        try {
            isUserExist = loadUserByUsername(registeredUserDto.telephone()) != null;
        } catch (UsernameNotFoundException ignored) {
        }

        if (isUserExist) throw new UserAlreadyExistException();

        User newUser = new User();
        newUser.setUsername(registeredUserDto.telephone());
        newUser.setPassword(passwordEncoder.encode(registeredUserDto.password()));
        newUser.setSecret(passwordEncoder.encode(registeredUserDto.secret()));
        newUser.setName(registeredUserDto.name());
        newUser.setSecondName(registeredUserDto.secondName());
        newUser.setEmail(registeredUserDto.email());

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

    @Override
    @Transactional
    public List<OrderDto> getOrdersByUsername(String username) {
        return ((User)loadUserByUsername(username))
            .getOrders()
            .stream()
            .map(orderDtoMapper)
            .toList();
    }

    @Override
    @Transactional
    public List<ReviewDto> getReviewsByUsername(String username) {
        return ((User)loadUserByUsername(username))
            .getReviews()
            .stream()
            .map(reviewDtoMapper)
            .toList();
    }

    @Override
    public UserDto getUserInfoByUsername(String username) {
        return userDtoMapper.apply(usersRepository.findUserByUsername(username));
    }
}
