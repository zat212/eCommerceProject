package com.lastProject.Repository;

import com.lastProject.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff,Integer> {

    Optional<Staff> findByStaffEmail(String email);
}
