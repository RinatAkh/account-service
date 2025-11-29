package com.rinat.controller;

import com.rinat.dto.User;
import com.rinat.dto.UserRegisterRequest;
import com.rinat.dto.UserRegisterResponse;
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
public class UserController implements UserApi{

    private final UserService userService;

    @Override
    public ResponseEntity<UserRegisterResponse> registerPost(UserRegisterRequest userRegisterRequest) {
        try {
            UserRegisterResponse response = userService.register(userRegisterRequest);
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
