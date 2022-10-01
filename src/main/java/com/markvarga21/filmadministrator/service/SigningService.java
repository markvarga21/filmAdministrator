package com.markvarga21.filmadministrator.service;

import com.markvarga21.filmadministrator.dto.UserSessionDTO;
import com.markvarga21.filmadministrator.entity.UserSession;
import com.markvarga21.filmadministrator.mapping.UserSessionMapper;
import com.markvarga21.filmadministrator.repository.UserSessionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class SigningService {
    private final UserSessionRepository userSessionRepository;
    private final UserSessionMapper userSessionMapper;

    public String logInAsAdmin(String userName, String password) {
        if (isSomeoneLoggedIn()) {
            return "Someone is already logged in, please log out first!";
        }
        if (userName.equals("admin") && password.equals("admin")) {
            UserSessionDTO userSessionDTO = new UserSessionDTO(userName);
            UserSession userSession = this.userSessionMapper.mapUserSessionDtoToEntity(userSessionDTO);
            this.userSessionRepository.save(userSession);
            return "Login success!";
        }
        return "Login failed due to incorrect credentials";
    }

    public boolean isSomeoneLoggedIn() {
        return this.userSessionRepository.findAll().size() != 0;
    }

    public String describeAccount() {
        if (!isSomeoneLoggedIn()) {
            return "You are not signed in";
        }
        var singedInUserEntity = this.userSessionRepository.findAll().get(0);
        String userName = singedInUserEntity.getUserName();
        if (userName.equals("admin")) {
            return String.format("Signed in with privileged account '%s'", userName);
        }
        return String.format("Signed in with account '%s'", userName);
    }
}
