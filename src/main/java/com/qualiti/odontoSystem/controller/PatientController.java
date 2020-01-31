package com.qualiti.odontoSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qualiti.odontoSystem.model.Patient;
import com.qualiti.odontoSystem.service.PatientService;

@RestController()
@RequestMapping("/api/v1/patients")
public class PatientController {

	private PatientService patientService;

	PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping
	public List findAll() {
		return patientService.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity findById(@PathVariable long id) {
		Optional<Patient> patient = patientService.findById(id);
		if (patient.isPresent()) {
			return ResponseEntity.ok().body(patient);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity create(@RequestBody Patient patient) {
		int createPatient = patientService.create(patient);
		if (createPatient == 0) {
			return ResponseEntity.ok().body("Usuário " + patient.getName() + " foi criado com sucesso!");
		} else if (createPatient == 1) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body("O CPF e/ou telefone fornecido(s) já existe(m).");
		} else if (createPatient == 2) {
			return ResponseEntity.badRequest().body("O nome preenchido contém caracteres inválidos.");
		} else if (createPatient == 3) {
			return ResponseEntity.badRequest().body("CPF inválido.");
		} else if (createPatient == 4) {
			return ResponseEntity.badRequest().body("Telefone inválido.");
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Patient patient) {
		Patient updatedPatient = patientService.update(id, patient);
		if (updatedPatient == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(updatedPatient);
		}
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity delete(@PathVariable long id) {
		patientService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
}