package com.rinat.mapper;

import com.rinat.dto.SendMessageRequest;
import com.rinat.model.MessageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageInfo toMessageInfo(SendMessageRequest sendMessageRequest);
}
