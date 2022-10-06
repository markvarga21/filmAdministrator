package com.markvarga21.filmadministrator.repository;

import com.markvarga21.filmadministrator.entity.RoomPriceAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomPriceAttachmentRepository extends JpaRepository<RoomPriceAttachment, Long> {
}
