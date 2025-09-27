package com.rinat.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
public class CreateChatRequestOld {

    private List<UUID> usersIds;
}
