package com.qualiti.odontoSystem.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "É necessário preencher o nome.")
	@NotEmpty(message = "É necessário preencher o nome.")
//	blocks all numbers and non-latin characters
	@Pattern(regexp = "[^0-9]*[^\\P{L}]*", message = "O nome preenchido contém caracteres inválidos.")
	@Column
	private String name;
	
	@NotNull(message = "É necessário preencher o CPF.")
	@NotEmpty(message = "É necessário preencher o CPF.")
	@Size(min = 11, max = 11, message = "CPF inválido.")
//	blocks all except numbers
	@Pattern(regexp = "[0-9]*", message = "CPF inválido.")
	@Column(length = 11, nullable = false, unique = true)
	private String cpf;
	
	@NotNull(message = "É necessário preencher o telefone.")
	@NotEmpty(message = "É necessário preencher o telefone.")
	@Size(min = 10, max = 11, message = "Telefone inválido.")
//	blocks all except numbers
	@Pattern(regexp = "[0-9]*", message = "Telefone inválido.")
	@Column(length = 11, nullable = false, unique = true)
	private String phone;
	
	@Column
	private Date birthday;
	
	@Enumerated
	public Gender gender;

	public static enum Gender {
		MALE, FEMALE, OTHER
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "patient")
	private List<Appointment> appointments;

	public Patient() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

}
