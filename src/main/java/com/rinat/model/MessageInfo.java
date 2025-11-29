package com.rinat.model;


import lombok.Data;

import java.util.UUID;

@Data
public class MessageInfo {
    private UUID senderUserId;
    private UUID chatId;
    private Message message;
}
