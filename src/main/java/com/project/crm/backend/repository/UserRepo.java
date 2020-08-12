package com.project.crm.backend.repository;

import com.project.crm.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByInn(Long inn);
    Optional<User> findByName(String name);
    boolean existsByDocumentNumber(String documentNumber);
    boolean existsByInn(Long inn);
    @Query(value = "select * from users u, registrations_journal rj where u.id = rj.user_id and rj.role_id = 3", nativeQuery = true)
    List<User> findAllDoctors();

    @Query(value = "select * from users u, registrations_journal rj where u.id = rj.user_id and rj.role_id = 5", nativeQuery = true)
    List<User> findAllPatients();

    @Query(value = "select * from users u, registrations_journal rj where u.id = rj.user_id and rj.role_id = 5 order by u.full_name asc", nativeQuery = true)
    Page<User> findAllPatients(Pageable pageable);

    @Query(value = "select * from users u, registrations_journal rj where u.id = rj.user_id order by u.full_name asc", nativeQuery = true)
    Page<User> findAll(Pageable pageable);

    @Query(value = "select * from users u, registrations_journal rj where u.id = rj.user_id and rj.role_id != 5 and rj.role_id != 1", nativeQuery = true)
    List<User> findAllHospitalStaff();

    @Query(value = "select * from users u, registrations_journal rj where u.id = rj.user_id and rj.role_id != 5 and rj.role_id != 1 order by u.full_name asc", nativeQuery = true)
    Page<User> findAllHospitalStaff(Pageable pageable);

    @Query(value = "select * from users u, registrations_journal rj where u.id = rj.user_id and rj.role_id = 2 order by u.full_name asc", nativeQuery = true)
    Page<User> findAllSeniorDoctors(Pageable pageable);
}
