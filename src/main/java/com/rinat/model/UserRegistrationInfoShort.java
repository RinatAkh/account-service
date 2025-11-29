package com.rinat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationInfoShort {
    private String name;
    private String surname;
    private String nickname;

}
