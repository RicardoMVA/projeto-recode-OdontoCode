package com.qualiti.odontoSystem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O campo paciente não pode ser nulo")
	@NotEmpty(message = "É necessário selecionar um nome.")
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

	@NotNull(message = "A data não pode ser nula.")
	@NotEmpty(message = "É necessário preencher a data da consulta.")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@NotNull(message = "O valor da consulta não pode ser nulo.")
	@NotEmpty(message = "É necessário preencher o valor da consulta.")
	@Size(min = 4, max = 7, message = "Valor da consulta com tamanho inválido.")
//	blocks all except numbers
	@Pattern(regexp = "[0-9]*", message = "Valor da consulta com caractere(s) inválido(s).")
	@Column
	private int value;

	@NotNull(message = "A especialidade não pode ser nula.")
	@NotEmpty(message = "É necessário preencher uma especialidade.")
//	blocks all numbers and non-latin characters
	@Pattern(regexp = "[^0-9]*[^\\P{L}]*", message = "A especialidae preenchida contém caracteres inválidos.")
	@Column
	private String specialty;

	@Column
	private boolean remarked;

	public Appointment() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public boolean isRemarked() {
		return remarked;
	}

	public void setRemarked(boolean remarked) {
		this.remarked = remarked;
	}

}
