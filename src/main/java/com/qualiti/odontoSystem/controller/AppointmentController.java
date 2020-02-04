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

import com.qualiti.odontoSystem.model.Appointment;
import com.qualiti.odontoSystem.service.AppointmentService;

@RestController()
@RequestMapping("/api/v1")

public class AppointmentController {

	private AppointmentService appointmentService;

	AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@GetMapping(path = "/appointments")
	public List findAll() {
		return appointmentService.findAll();
	}

	@GetMapping(path = { "/patients/{patientId}/appointments/{id}" })
	public ResponseEntity<?> findById(@PathVariable long id) {
		Optional<Appointment> appointment = appointmentService.findById(id);
		if (appointment.isPresent()) {
			return ResponseEntity.ok().body(appointment);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(path = { "/patients/{patientId}/appointments" })
	public ResponseEntity<?> create(@PathVariable(value = "patientId") Long patientId,
			@RequestBody Appointment appointment) {
		Appointment createAppointment = appointmentService.create(patientId, appointment);
		if (createAppointment == null) {

			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(appointment);
		}
	}

	@PutMapping(path = { "/patients/{patientId}/appointments/{id}" })
	public ResponseEntity<?> update(@PathVariable("patientId") long patientId, @PathVariable("id") long id,
			@RequestBody Appointment appointment) {
		Appointment updateAppointment = appointmentService.update(patientId, id, appointment);
		if (updateAppointment == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(updateAppointment);
		}
	}

	@DeleteMapping(path = { "/patients/{patientId}/appointments/{id}" })
	public ResponseEntity<?> delete(@PathVariable("patientId") long patientId, @PathVariable("id") long id) {
		appointmentService.delete(patientId, id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
