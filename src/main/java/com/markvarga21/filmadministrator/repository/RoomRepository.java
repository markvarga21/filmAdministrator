package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    int deleteByName(String name);
}
