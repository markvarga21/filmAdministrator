package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getUserByUserName(String userName);
}
