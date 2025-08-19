package com.rinat.service;

import com.rinat.model.UserRegistrationInfo;
import com.rinat.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void register (UserRegistrationInfo userInfo) {
        if(accountValidation(userInfo)) {
            throw  new IllegalArgumentException("Аккаунт с таким никнеймом уже существует");
        }
        accountRepository.saveAccount(userInfo);
    }

    private boolean accountValidation(UserRegistrationInfo userInfo) {
        return accountRepository.exist(userInfo.getNickname());
    }

}
