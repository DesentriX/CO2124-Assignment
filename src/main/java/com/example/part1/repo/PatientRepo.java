package com.example.part1.repo;

import com.example.part1.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PatientRepo extends JpaRepository<Patient, Long> {
    public Patient findByName(String name);

}
