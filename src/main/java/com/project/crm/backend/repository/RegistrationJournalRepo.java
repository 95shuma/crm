package com.project.crm.backend.repository;

import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RegistrationJournalRepo extends JpaRepository<RegistrationJournal, Long> {
    List<RegistrationJournal> findByHospitalId(Long hospitalId);
    List<RegistrationJournal> findByHospitalIdAndRoleId(Long hospitalId, Long roleId);
    List<RegistrationJournal> findByHospitalIdAndPositionId(Long hospitalId, Long positionId);
    RegistrationJournal findByUserInn(Long inn);
    RegistrationJournal findFirstByUserInnAndRoleId(Long inn, Long roleId);

    @Query(value = "select * from users u, registrations_journal rj where u.id = rj.user_id and rj.role_id = 2 order by u.full_name asc", nativeQuery = true)
    Page<RegistrationJournal> findAllSeniorDoctors(Pageable pageable);

    @Query(value = "select * from users u, registrations_journal rj where u.id = rj.user_id and rj.role_id != 5 and rj.role_id != 1  and rj.hospital_id = ?1   order by u.full_name asc", nativeQuery = true)
    Page<RegistrationJournal> findAllHospitalStaff(Long hospitalId, Pageable pageable);

    boolean existsByUserInnAndRoleId(Long inn, Long id);
}
