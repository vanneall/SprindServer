package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.TokenDto;
import ru.point.entity.dto.UserDto;
import ru.point.security.token.TokenGenerator;
import ru.point.service.implementations.UsersService;

import java.security.SecureRandom;

@AllArgsConstructor
@RestController
@RequestMapping("/sprind/auth")
public class AuthorizationRestController {

    TokenGenerator tokenGenerator;

    UsersService usersService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping()
    public void registration(@RequestBody UserDto userDto) {
        usersService.save(userDto);
    }

    @GetMapping()
    public TokenDto authorization(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        UserDto userDto = new UserDto(username, password);
        UserDetails userDetails = usersService.loadUserByUsername(userDto.username());
        return new TokenDto(tokenGenerator.apply(userDetails));
    }
}
