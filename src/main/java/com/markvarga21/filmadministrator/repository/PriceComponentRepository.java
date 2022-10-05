package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.PriceComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceComponentRepository extends JpaRepository<PriceComponent, String> {
}
