package com.markvarga21.filmadministrator.service;

import com.markvarga21.filmadministrator.dto.UserDTO;
import com.markvarga21.filmadministrator.dto.UserSessionDTO;
import com.markvarga21.filmadministrator.entity.User;
import com.markvarga21.filmadministrator.entity.UserSession;
import com.markvarga21.filmadministrator.mapping.UserMapper;
import com.markvarga21.filmadministrator.mapping.UserSessionMapper;
import com.markvarga21.filmadministrator.repository.UserRepository;
import com.markvarga21.filmadministrator.repository.UserSessionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Data
@RequiredArgsConstructor
public class SigningService {
    private final UserSessionRepository userSessionRepository;
    private final UserRepository userRepository;
    private final UserSessionMapper userSessionMapper;
    private final UserMapper userMapper;

    @Transactional
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

    @Transactional
    public String logInUser(String userName, String password) {
        var userOptional = this.userRepository.getUserByUserName(userName);
        if (userOptional.isEmpty()) {
            return String.format("There is no user signed up with username '%s'", userName);
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            return String.format("Wrong password entered for user '%s'", userName);
        }

        if (isSomeoneLoggedIn()) {
            return "Someone is already logged in, please log out first!";
        }

        UserSessionDTO userSessionDTO = new UserSessionDTO(userName);
        this.userSessionRepository.save(this.userSessionMapper.mapUserSessionDtoToEntity(userSessionDTO));
        return "Login successful!";
    }

    @Transactional
    public String signUpUser(String userName, String password) {
        UserDTO userDTO = new UserDTO(userName, password);
        this.userRepository.save(this.userMapper.mapUserDtoToEntity(userDTO));
        return String.format("User '%s' signed up successfully!", userName);
    }

    @Transactional
    public String signOutUser() {
        if (!isSomeoneLoggedIn()) {
            return "You cannot sign out, because no one is logged in!";
        }
        UserSession userSession = this.userSessionRepository.findAll().get(0);
        String userName = userSession.getUserName();

        this.userSessionRepository.deleteUserSessionByUserName(userName);
        return String.format("User '%s' logged out successfully", userName);
    }

    public boolean isAdminLoggedIn() {
        var userSessions = this.userSessionRepository.findAll();
        if (userSessions.size() != 0) {
            String loggedInUser = userSessions.get(0).getUserName();
            return loggedInUser.equals("admin");
        }
        return false;
    }

    public String getLoggedInUser() {
        return this.userRepository
                .findAll()
                .get(0)
                .getUserName();
    }
}
