package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.ResetUserDto;
import ru.point.entity.dto.TokenDto;
import ru.point.entity.dto.RegisteredUserDto;
import ru.point.service.interfaces.UserSecurityService;
import ru.point.service.interfaces.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/sprind/auth")
public class AuthorizationRestController {

    private final UserSecurityService userSecurityService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void registration(@RequestBody RegisteredUserDto registeredUserDto) {
        userSecurityService.createNewUser(registeredUserDto);
    }

    @GetMapping()
    public TokenDto authorization(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        return userSecurityService.generateAuthorizationTokenForUser(username, password);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/reset")
    public void resetPassword(@RequestBody ResetUserDto resetUserDto) {
        userSecurityService.resetUserPassword(resetUserDto.username(), resetUserDto.secret(), resetUserDto.newPassword());
    }
}
