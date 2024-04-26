package ru.point.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.UserDto;
import ru.point.entity.table.User;
import ru.point.security.JwtUtils;
import ru.point.service.interfaces.UsersService;

@RestController
@RequestMapping("/sprind/auth")
public class AuthorizationRestController {

    UsersService usersService;

    AuthorizationRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/reg")
    public void registration(@RequestBody UserDto userDto) {
        usersService.save(userDto);
    }

    @GetMapping()
    public String authorization(@RequestBody UserDto userDto) {
        UserDetails userDetails = usersService.loadUserByUsername(userDto.username());

        return JwtUtils.generateToken(userDetails);
    }
}
