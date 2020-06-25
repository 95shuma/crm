package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.remediesCatalog.InternationalName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternationalNameRepo extends JpaRepository<InternationalName, Long> {

}
