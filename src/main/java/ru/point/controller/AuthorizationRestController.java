package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.UserDto;
import ru.point.security.token.TokenGenerator;
import ru.point.service.implementations.UsersService;

@AllArgsConstructor
@RestController
@RequestMapping("/sprind/auth")
public class AuthorizationRestController {

    TokenGenerator tokenGenerator;

    UsersService usersService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/reg")
    public void registration(@RequestBody UserDto userDto) {
        usersService.save(userDto);
    }

    @GetMapping()
    public String authorization(@RequestBody UserDto userDto) {
        UserDetails userDetails = usersService.loadUserByUsername(userDto.username());
        return tokenGenerator.apply(userDetails);
    }
}
