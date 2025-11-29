package com.rinat.dto;

import lombok.*;

import java.util.Date;

@Data
public class UserRegistrationRequest {
    private String name;
    private String surname;
    private String nickname;
    private Date dateOfBirth;
    private String email;
    private String password;
}
