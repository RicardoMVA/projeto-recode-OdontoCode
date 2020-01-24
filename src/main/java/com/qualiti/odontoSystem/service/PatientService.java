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
			currentPatient.get().setName(patient.getName());
			currentPatient.get().setCPF(patient.getCPF());
			currentPatient.get().setPhone(patient.getPhone());
			currentPatient.get().setBirthday(patient.getBirthday());
			currentPatient.get().setGender(patient.getGender());
			if (patient.getAppointments() != null) {
				currentPatient.get().getAppointments().addAll(patient.getAppointments());
			}
			return patientRepository.save(currentPatient.get());
		} else {
			throw new ResourceNotFoundException("Pacient", "Client", "O paciente com id:" + id + " não foi encontrado");
		}

	}

	public void delete(long id) {
		try {
			patientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}

	}
}
