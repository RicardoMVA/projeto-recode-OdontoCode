package com.qualiti.odontoSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
}