package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.Screening;
import com.markvarga21.filmadministrator.util.CompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, CompositeKey> {
}
