package com.rinat.controller;

import com.rinat.dto.CreateChatRequest;
import com.rinat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    @PostMapping("/create")
    public ResponseEntity<String> createChat(@RequestBody CreateChatRequest request) {
        if(request.getUsersIds() == null)
            throw new IllegalArgumentException("Список пустой");
        if(request.getUsersIds().size() != 2) {
            throw new IllegalArgumentException("Список не содержит всех пользователей");
        }

        chatService.createChat(request);
        return  ResponseEntity.ok("Ваш чат успешно создан");
    }

}
