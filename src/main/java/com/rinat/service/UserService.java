package com.rinat.service;

import com.rinat.dto.UserRegisterRequest;
import com.rinat.dto.UserRegisterResponse;
import com.rinat.dto.UserRegistrationRequest;
import com.rinat.mapper.UserMapper;
import com.rinat.model.UserRegistrationInfo;
import com.rinat.model.UserRegistrationInfoShort;
import com.rinat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRegisterResponse register (UserRegisterRequest userInfo) {
        //TODO сделать в одной транзакции
        if(accountValidation(userInfo)) {
            throw  new IllegalArgumentException ("Аккаунт с таким никнеймом уже существует");
        }
        UserRegistrationInfo registrationInfo = userMapper.toUserRegistrationInfo(userInfo);
        UserRegistrationInfoShort registrationInfoShort = userRepository.save(registrationInfo);
        return userMapper.toUserRegistrationInfoResponse(registrationInfoShort);
    }

    private boolean accountValidation(UserRegisterRequest userInfo) {
        return userRepository.exist(userInfo.getNickname());
    }

    public List<String> getNicknames() {
        return userRepository.getNicknames();
    }
}
