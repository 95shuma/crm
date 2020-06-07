package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.RegistrationPlace;
import com.project.crm.backend.model.catalog.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
public interface RoleRepo extends JpaRepository<Role, Long> {
     Role findByName(String n);
     Role findRoleById(Long id);
     @Modifying
     @Query(value = "insert into roles (id, name)  values (:id, :name);", nativeQuery = true)
     void insertRoleWithId(@Param("id") Long id, @Param("name") String name);
}
