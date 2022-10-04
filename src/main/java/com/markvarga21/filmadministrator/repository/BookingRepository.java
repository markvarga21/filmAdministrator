package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.Booking;
import com.markvarga21.filmadministrator.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<List<Booking>> getBookingsByUserName(String userName);
}
