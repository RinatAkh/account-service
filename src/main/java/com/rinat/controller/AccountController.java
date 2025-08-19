package com.rinat.controller;

import com.rinat.model.UserRegistrationInfo;
import com.rinat.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationInfo registrationInfo) {
        accountService.register(registrationInfo);
        return ResponseEntity.ok("Все прошло успешно");
    }
}
