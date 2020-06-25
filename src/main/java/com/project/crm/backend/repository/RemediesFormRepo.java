package com.project.crm.backend.repository;


import com.project.crm.backend.model.catalog.remediesCatalog.RemediesForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemediesFormRepo extends JpaRepository<RemediesForm, Long> {

}
