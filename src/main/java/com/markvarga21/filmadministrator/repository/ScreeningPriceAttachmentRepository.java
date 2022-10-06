package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.ScreeningPriceAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningPriceAttachmentRepository extends JpaRepository<ScreeningPriceAttachment, Long> {
}
