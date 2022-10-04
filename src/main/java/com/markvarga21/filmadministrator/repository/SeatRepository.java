package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.Seat;
import com.markvarga21.filmadministrator.util.SeatCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, SeatCompositeKey> {
}
