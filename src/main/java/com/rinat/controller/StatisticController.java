package com.rinat.controller;

import com.rinat.dto.UserStatisticInfo;
import com.rinat.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatisticController implements StatisticsApi{

    private final StatisticService statisticService;
    @Override
    public ResponseEntity<List<UserStatisticInfo>> statisticsGet() {
        return ResponseEntity.ok(statisticService.getUsersStatistic());
    }
}
