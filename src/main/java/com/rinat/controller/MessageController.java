package com.rinat.controller;

import com.rinat.dto.SendMessageRequest;
import com.rinat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController implements MessageApi {

    private final MessageService messageService;
    @Override
    public ResponseEntity<Void> messagesPost(SendMessageRequest sendMessageRequest) {
        messageService.sendMessage(sendMessageRequest);
        return ResponseEntity.ok().build();
    }
}
