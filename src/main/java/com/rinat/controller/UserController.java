package com.rinat.controller;

import com.rinat.dto.UserRegistrationRequest;
import com.rinat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest registrationInfo) {
        try {
            userService.register(registrationInfo);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        return ResponseEntity.ok("Все прошло успешно");
    }

    @GetMapping("/nicknames")
    public ResponseEntity<List<String>> getUserNicknames() {
        return ResponseEntity.ok(userService.getNicknames());
    }


}
