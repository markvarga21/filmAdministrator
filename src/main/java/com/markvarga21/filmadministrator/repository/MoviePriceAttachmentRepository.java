package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.MoviePriceAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviePriceAttachmentRepository extends JpaRepository<MoviePriceAttachment, Long> {
}
