package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.remediesCatalog.Dosage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DosageRepo extends JpaRepository<Dosage, Long> {

}
