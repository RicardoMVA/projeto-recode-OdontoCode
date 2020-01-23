package com.qualiti.odontoSystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}