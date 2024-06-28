package ru.point.service.implementations;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.*;
import ru.point.entity.mapper.OrderToOrderDtoMapper;
import ru.point.entity.mapper.ReviewToReviewDtoMapper;
import ru.point.entity.mapper.UserToUserDtoMapper;
import ru.point.entity.table.Address;
import ru.point.entity.table.User;
import ru.point.repository.interfaces.UsersRepository;
import ru.point.service.interfaces.UserService;
import ru.point.service.interfaces.horizontal.UserServiceHorizontal;

import java.util.List;

@AllArgsConstructor
@Service
public class UsersServiceImpl implements UserDetailsService, UserService, UserServiceHorizontal {

    private final UserToUserDtoMapper userDtoMapper;
    private final ReviewToReviewDtoMapper reviewDtoMapper;
    private final OrderToOrderDtoMapper orderDtoMapper;
    private final UsersRepository usersRepository;

    @Override
    public void saveNewUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = usersRepository.findUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        return user;
    }

    @Override
    @Transactional
    public List<OrderDto> getOrdersByUsername(String username) {
        return ((User) loadUserByUsername(username))
            .getOrders()
            .stream()
            .map(orderDtoMapper)
            .toList();
    }

    @Override
    @Transactional
    public List<ReviewDto> getReviewsByUsername(String username) {
        return ((User) loadUserByUsername(username))
            .getReviews()
            .stream()
            .map(reviewDtoMapper)
            .toList();
    }

    @Override
    public UserDto getUserInfoByUsername(String username) {
        return userDtoMapper.apply(usersRepository.findUserByUsername(username));
    }

    @Override
    @Transactional
    public void setAddress(Address address, String username) {
        User user = usersRepository.findUserByUsername(username);
        user.setAddress(address);
    }
}
