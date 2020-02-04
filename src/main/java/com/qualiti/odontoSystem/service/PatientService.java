package com.qualiti.odontoSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qualiti.odontoSystem.exception.InvalidFormDataException;
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

	public Patient create(Patient patient) throws InvalidFormDataException {
		Matcher checkName = namePattern.matcher(patient.getName());
		Matcher checkCPF = numberPattern.matcher(patient.getCPF());
		Matcher checkPhone = numberPattern.matcher(patient.getPhone());

		if (!checkName.matches()) {
			throw new InvalidFormDataException("O nome preenchido contém caracteres inválidos.");
		} else if (checkCPF.matches() == false || patient.getCPF().length() != 11 || patient.getCPF() == null) {
			throw new InvalidFormDataException("CPF inválido.");
		} else if (checkPhone.matches() == false || patient.getPhone().length() < 10 || patient.getPhone().length() > 11
				|| patient.getPhone() == null) {
			throw new InvalidFormDataException("Telefone inválido.");
		} else {
			try {
				return patientRepository.save(patient);
			} catch (DataIntegrityViolationException e) {
				throw new InvalidFormDataException("CPF e/ou telefone já cadastrado(s).");
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
		}
		return null;
	}

	public void delete(long id) {
		try {
			patientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
