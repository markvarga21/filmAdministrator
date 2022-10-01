package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
}
