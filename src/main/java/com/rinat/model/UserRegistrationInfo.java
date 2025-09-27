package com.rinat.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserRegistrationInfo {
    private String name;
    private String surname;
    private String nickname;
    private Date dateOfBirth;
    private String email;
    private String password;
}
