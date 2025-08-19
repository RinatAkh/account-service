package com.rinat.controller;

import com.rinat.model.CreateChatRequest;
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
        chatService.createChat(request);
        return  ResponseEntity.ok("Ваш чат успешно создан");
    }

}
