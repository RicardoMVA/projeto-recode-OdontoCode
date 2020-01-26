package com.qualiti.odontoSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qualiti.odontoSystem.exception.ResourceNotFoundException;
import com.qualiti.odontoSystem.model.Appointment;
import com.qualiti.odontoSystem.model.Patient;
import com.qualiti.odontoSystem.repository.AppointmentRepository;
import com.qualiti.odontoSystem.repository.PatientRepository;

@Service
public class AppointmentService {

	private AppointmentRepository appointmentRepository;
	@Autowired
	private PatientRepository patientRepository;

	AppointmentService(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	public List findAll() {
		return appointmentRepository.findAll();
	}

	public Optional<Appointment> findById(long id) {
		return appointmentRepository.findById(id);
	}

	public Appointment create(Long patientId, Appointment appointment) {
		Optional<Patient> currentPatient = patientRepository.findById(patientId);
		if (currentPatient.isPresent()) {
			appointment.setPatient(currentPatient.get());
			return appointmentRepository.save(appointment);
		} else {
			throw new ResourceNotFoundException("Appointment", "Patient_Id",
					"O paciente com id: " + patientId + " não foi encontrado");
		}
	}

	public Appointment update(long patientId, long id, Appointment appointment) {
		Optional<Patient> currentPatient = patientRepository.findById(patientId);
		if (currentPatient.isPresent()) {
			Optional<Appointment> currentAppointment = appointmentRepository.findById(id);
			if (currentAppointment.isPresent()) {
				Appointment oldAppointment = currentAppointment.get();
				oldAppointment.setDate(appointment.getDate());
				oldAppointment.setSpecialty(appointment.getSpecialty());
				oldAppointment.setValue(appointment.getValue());
				oldAppointment.setRemarked(appointment.isRemarked());

				return appointmentRepository.save(currentAppointment.get());
			} else {
				throw new ResourceNotFoundException("Appointment", "Id",
						"A consulta com id: " + id + " não foi encontrada");
			}
		} else {
			throw new ResourceNotFoundException("Appointment", "Patient_Id",
					"O paciente com id: " + patientId + " não foi encontrado");
		}
	}

	public void delete(long patientId, long id) {
		Optional<Patient> currentPatient = patientRepository.findById(patientId);
		Optional<Appointment> currentAppointment = appointmentRepository.findById(id);

		if (currentPatient.isPresent() && currentAppointment.isPresent()) {
			try {
				List<Appointment> patientAppointments = currentPatient.get().getAppointments();
				int indexOfAppointment = patientAppointments.indexOf(currentAppointment.get());
				patientAppointments.remove(indexOfAppointment);
				currentPatient.get().setAppointments(patientAppointments);

				appointmentRepository.deleteById(id);
			} catch (EmptyResultDataAccessException e) {
			}
		}
	}
}
