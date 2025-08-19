package com.rinat.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateChatRequest {
    private List<UUID> usersIds;
}
