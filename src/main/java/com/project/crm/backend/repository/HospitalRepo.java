package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HospitalRepo extends JpaRepository<Hospital, Long> {

    Optional<Hospital> findByName(String name);

    @Query(value = "select * from hospitals as h order by h.name asc", nativeQuery = true)
    Page<Hospital> findAll(Pageable page);
}
