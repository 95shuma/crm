package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.RegistrationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface RegistrationTypeRepo extends JpaRepository<RegistrationType, Long> {
    RegistrationType findByName(String name);
    @Modifying
    @Query(value = "insert into registration_types (id, name)  values (:id, :name);", nativeQuery = true)
    void insertTypesWithId(@Param("id") Long id, @Param("name") String name);
}
