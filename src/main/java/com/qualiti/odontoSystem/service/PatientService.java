package com.qualiti.odontoSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qualiti.odontoSystem.exception.ResourceNotFoundException;
import com.qualiti.odontoSystem.model.Patient;
import com.qualiti.odontoSystem.repository.PatientRepository;

@Service
public class PatientService {

	private PatientRepository patientRepository;
//	blocks all numbers and non-latin characters
	Pattern namePattern = Pattern.compile("[^0-9]*[^\\P{L}]*");
//	only allows numbers
	Pattern numberPattern = Pattern.compile("[0-9]*");

	PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public List findAll() {
		return patientRepository.findAll();
	}

	public Optional<Patient> findById(long id) {
		return patientRepository.findById(id);
	}

	public int create(Patient patient) {
		Matcher checkName = namePattern.matcher(patient.getName());
		Matcher checkCPF = numberPattern.matcher(patient.getCPF());
		Matcher checkPhone = numberPattern.matcher(patient.getPhone());

		if (checkName.matches() == false) {
			return 2;
		} else if (checkCPF.matches() == false || patient.getCPF().length() != 11 || patient.getCPF() == null) {
			return 3;
		} else if (checkPhone.matches() == false || patient.getPhone().length() < 10 || patient.getPhone().length() > 11
				|| patient.getPhone() == null) {
			return 4;
		} else {
			try {
				patientRepository.save(patient);
				return 0;
			} catch (DataIntegrityViolationException e) {
				return 1;
			}
		}
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
