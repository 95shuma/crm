package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.Disease;
import com.project.crm.backend.model.catalog.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepo extends JpaRepository<Disease, Long> {

}
