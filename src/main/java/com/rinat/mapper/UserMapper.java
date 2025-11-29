package com.rinat.mapper;

import com.rinat.dto.UserRegisterRequest;
import com.rinat.dto.UserRegisterResponse;
import com.rinat.dto.UserRegistrationRequest;
import com.rinat.model.UserRegistrationInfo;
import com.rinat.model.UserRegistrationInfoShort;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRegistrationInfo toUserRegistrationInfo(UserRegisterRequest userRegisterRequest);

    UserRegisterResponse toUserRegistrationInfoResponse(UserRegistrationInfoShort userRegisterInfoShort);

}
