package com.markvarga21.filmadministrator.mapping;

import com.markvarga21.filmadministrator.dto.UserDTO;
import com.markvarga21.filmadministrator.dto.UserSessionDTO;
import com.markvarga21.filmadministrator.entity.User;
import com.markvarga21.filmadministrator.entity.UserSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDTO mapUserToDto(User user) {
        return this.modelMapper.map(user, UserDTO.class);
    }

    public User mapUserDtoToEntity(UserDTO userDTO) {
        return this.modelMapper.map(userDTO, User.class);
    }
}
