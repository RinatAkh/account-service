package com.rinat.service;

import com.rinat.model.CreateChatRequest;
import com.rinat.repository.ChatRepository;
import com.rinat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    // Нам нужно проверить если у таких пользователей общий чат
    // Что проверяем? Что нельзя создать чат с уже существующими
    // Что нельзя создаь чат с одним и тем же пользователем
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    public void createChat(CreateChatRequest request) {
        if(userRepository.exist(request) && !chatRepository.exist(request)){
            chatRepository.saveChat(request);
        } else {
            throw new IllegalArgumentException("К сожалению, пользователи не найдены или такой чат уже существует");
        }
    }
}
