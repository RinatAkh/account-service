package com.rinat.service;

import com.rinat.dto.UserStatisticInfo;
import com.rinat.repository.MessageRepository;
import com.rinat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final MessageRepository userRepository;
    public List<UserStatisticInfo> getUsersStatistic () {
        return userRepository.getUsersStatistic();
    }
}
