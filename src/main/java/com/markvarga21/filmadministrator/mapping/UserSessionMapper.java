package com.markvarga21.filmadministrator.mapping;

import com.markvarga21.filmadministrator.dto.UserSessionDTO;
import com.markvarga21.filmadministrator.entity.UserSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSessionMapper {
    private final ModelMapper modelMapper;

    public UserSessionDTO mapUserSessionToDto(UserSession userSession) {
        return this.modelMapper.map(userSession, UserSessionDTO.class);
    }

    public UserSession mapUserSessionDtoToEntity(UserSessionDTO userSessionDTO) {
        return this.modelMapper.map(userSessionDTO, UserSession.class);
    }
}
