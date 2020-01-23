package com.qualiti.odontoSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qualiti.odontoSystem.model.Patient;
import com.qualiti.odontoSystem.repository.PatientRepository;

@Service
public class PatientService {

	private PatientRepository patientRepository;

	PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	public List findAll() {
		return patientRepository.findAll();
	}
	
	public Optional<Patient> findById(long id) {
		return patientRepository.findById(id);
	}

	
	public Patient create(Patient patient) {
		return patientRepository.save(patient);
	}
}