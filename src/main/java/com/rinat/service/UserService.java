package com.rinat.service;

import com.rinat.model.UserRegistrationInfo;
import com.rinat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void register (UserRegistrationInfo userInfo) {
        //TODO сделать в одной транзакции
        if(accountValidation(userInfo)) {
            throw  new IllegalArgumentException ("Аккаунт с таким никнеймом уже существует");
        }
        userRepository.save(userInfo);
    }



    private boolean accountValidation(UserRegistrationInfo userInfo) {
        return userRepository.exist(userInfo.getNickname());
    }


    public List<String> getNicknames() {
        return userRepository.getNicknames();
    }
}
