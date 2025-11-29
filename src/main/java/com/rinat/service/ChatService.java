package com.rinat.service;

import com.rinat.dto.CreateChatRequest;
import com.rinat.dto.CreateChatRequestOld;
import com.rinat.repository.ChatRepository;
import com.rinat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public void createChat(CreateChatRequest request) {
        List<UUID> usersIds = request.getUsersIds();
        if(!userRepository.exist(usersIds))
            throw new IllegalArgumentException("К сожалению, пользователь или пользователи не найдены");
        if (chatRepository.exist(usersIds))
            throw new IllegalArgumentException("К сожалению, такой чат уже существует");

        UUID chatId = chatRepository.saveToChat();
        chatRepository.saveToChatUsers(request.getUsersIds(), chatId);
    }
}
