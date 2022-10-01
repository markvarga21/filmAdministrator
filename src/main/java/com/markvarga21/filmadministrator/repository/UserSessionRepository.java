package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, String> {
    void deleteUserSessionByUserName(String userName);
}
