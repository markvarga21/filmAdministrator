package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.BasePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasePriceRepository extends JpaRepository<BasePrice, Long> {
}
