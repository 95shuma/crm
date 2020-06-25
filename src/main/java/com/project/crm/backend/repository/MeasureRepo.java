package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.remediesCatalog.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepo extends JpaRepository<Measure, Long> {

}
