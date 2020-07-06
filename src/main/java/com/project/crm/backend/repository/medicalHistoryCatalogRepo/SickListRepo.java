package com.project.crm.backend.repository.medicalHistoryCatalogRepo;


import com.project.crm.backend.model.catalog.medicalHistoryCatalog.SickList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SickListRepo extends JpaRepository<SickList, Long> {

    Optional<SickList> findByNumber(Long number);

    @Query(value = "select * from sick_lists as s order by s.start_date asc", nativeQuery = true)
    Page<SickList> findAll(Pageable page);
}
