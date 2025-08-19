package com.rinat.controller;

import com.rinat.model.UserRegistrationInfo;
import com.rinat.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationInfo registrationInfo) {
        try {
            accountService.register(registrationInfo);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        return ResponseEntity.ok("Все прошло успешно");
    }

    @GetMapping("/nicknames")
    public ResponseEntity<List<String>> getUserNicknames() {
        return ResponseEntity.ok(accountService.getNicknames());
    }


}
