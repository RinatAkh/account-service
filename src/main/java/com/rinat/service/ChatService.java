package com.rinat.service;

import com.rinat.model.CreateChatRequest;
import com.rinat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    public void createChat(CreateChatRequest request) {
        // TODO 1. проверить, что такие id есть в бд иначе ошибка(должна быть видна на фронта)
        // 2. сохранить чат в + вернуть 200 ок
        chatRepository.createChat(request);
    }
}
