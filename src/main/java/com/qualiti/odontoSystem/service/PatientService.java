package com.qualiti.odontoSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qualiti.odontoSystem.exception.ResourceNotFoundException;
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

	public Patient update(long id, Patient patient) {
		Optional<Patient> currentPatient = patientRepository.findById(id);
		if (currentPatient.isPresent()) {
			Patient oldPatient = currentPatient.get();
			oldPatient.setName(patient.getName());
			oldPatient.setCPF(patient.getCPF());
			oldPatient.setPhone(patient.getPhone());
			oldPatient.setBirthday(patient.getBirthday());
			oldPatient.setGender(patient.getGender());
			if (patient.getAppointments() != null) {
				oldPatient.getAppointments().addAll(patient.getAppointments());
			}
			return patientRepository.save(currentPatient.get());
		} else {
			throw new ResourceNotFoundException("Patient", "Id", "O paciente com id: " + id + " não foi encontrado");
		}

	}

	public void delete(long id) {
		try {
			patientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}

	}
}
