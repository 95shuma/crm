package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface PositionRepo extends JpaRepository<Position, Long> {

    Optional<Position> findByName(String name);
    Optional<Position> getById(Long id);
    @Modifying
    @Query(value = "insert into positions (id, name)  values (:id, :name);", nativeQuery = true)
    void insertPositionWithId(@Param("id") Long id, @Param("name") String name);

}
