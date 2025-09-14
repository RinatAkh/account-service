package com.rinat.service;

import com.rinat.dto.UserRegistrationRequest;
import com.rinat.mapper.UserMapper;
import com.rinat.model.UserRegistrationInfo;
import com.rinat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void register (UserRegistrationRequest userInfo) {
        //TODO сделать в одной транзакции
        if(accountValidation(userInfo)) {
            throw  new IllegalArgumentException ("Аккаунт с таким никнеймом уже существует");
        }
        UserRegistrationInfo registrationInfo = userMapper.toUserRegistrationInfo(userInfo);
        userRepository.save(registrationInfo);
    }

    private boolean accountValidation(UserRegistrationRequest userInfo) {
        return userRepository.exist(userInfo.getNickname());
    }

    public List<String> getNicknames() {
        return userRepository.getNicknames();
    }
}
