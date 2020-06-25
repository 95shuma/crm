package com.project.crm.backend.repository;


import com.project.crm.backend.model.catalog.remediesCatalog.RemedyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemedyTypeRepo extends JpaRepository<RemedyType, Long> {

}
