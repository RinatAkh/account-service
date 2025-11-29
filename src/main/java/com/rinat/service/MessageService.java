package com.rinat.service;

import com.rinat.dto.SendMessageRequest;
import com.rinat.mapper.MessageMapper;
import com.rinat.model.MessageInfo;
import com.rinat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper mapper;

    public void sendMessage(SendMessageRequest sendMessageRequest) {
        MessageInfo messageInfo = mapper.toMessageInfo(sendMessageRequest);
        messageRepository.save(messageInfo);
    }
}
