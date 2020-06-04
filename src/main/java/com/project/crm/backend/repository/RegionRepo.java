package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepo extends JpaRepository<Region, Long> {
    Region findRegionById(Long id);
}
