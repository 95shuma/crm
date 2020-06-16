package com.project.crm.backend.repository;

import com.project.crm.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByInn(String inn);
    Optional<User> findByName(String name);
    boolean existsByInn(String inn);
    @Query(value = "select u.* from users u, registrations_journal rj where u.id = rj.user_id and rj.role_id = 5", nativeQuery = true)
    List<User> findAllPatients();

    @Query(value = "select * from users u, registrations_journal rj where u.id = rj.user_id and rj.role_id != 5 and rj.role_id != 1", nativeQuery = true)
    List<User> findAllHospitalStaff();

    @Query(value = "select * from users u, registrations_journal rj where u.id = rj.user_id and rj.role_id != 5 and rj.role_id != 1", nativeQuery = true)
    Page<User> findAllHospitalStaffPageable(Pageable pageable);
}
