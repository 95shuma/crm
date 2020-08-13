package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.RegistrationJournal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationJournalRepo extends JpaRepository<RegistrationJournal, Long> {
    List<RegistrationJournal> findByHospitalId(Long hospitalId);
    List<RegistrationJournal> findByHospitalIdAndRoleId(Long hospitalId, Long roleId);
    List<RegistrationJournal> findByHospitalIdAndPositionId(Long hospitalId, Long positionId);
    RegistrationJournal findByUserInn(Long inn);
    RegistrationJournal findFirstByUserInnAndRoleId(Long inn, Long roleId);
    boolean existsByUserInnAndRoleId(Long inn, Long id);
}
