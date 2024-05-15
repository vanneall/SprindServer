package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.OrderDto;
import ru.point.entity.dto.ReviewDto;
import ru.point.entity.dto.UserDto;
import ru.point.entity.table.Address;
import ru.point.service.interfaces.UserService;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("sprind/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    UserDto handleUserEndpoint(Principal principal) {
        return userService.getUserInfoByUsername(principal.getName());
    }

    @GetMapping("/reviews")
    List<ReviewDto> handleUserReviewsEndpoint(Principal principal) {
        return userService.getReviewsByUsername(principal.getName());
    }

    @GetMapping("/orders")
    List<OrderDto> handleUserOrdersEndpoint(Principal principal) {
        return userService.getOrdersByUsername(principal.getName());
    }

    @PatchMapping("/address")
    void handleChangeAddressEndpoint(@RequestBody Address address, Principal principal) {
        userService.setAddress(address, principal.getName());
    }
}
