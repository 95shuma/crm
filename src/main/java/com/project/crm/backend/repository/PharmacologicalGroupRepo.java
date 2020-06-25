package com.project.crm.backend.repository;


import com.project.crm.backend.model.catalog.remediesCatalog.PharmacologicalGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacologicalGroupRepo extends JpaRepository<PharmacologicalGroup, Long> {

}
