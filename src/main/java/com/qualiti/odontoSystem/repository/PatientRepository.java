package com.qualiti.odontoSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qualiti.odontoSystem.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient> findByNameContaining(String name);

	List<Patient> findByCPFContaining(String CPF);
}
