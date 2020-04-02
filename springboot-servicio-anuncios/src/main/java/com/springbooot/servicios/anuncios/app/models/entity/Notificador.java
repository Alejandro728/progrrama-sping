package com.springbooot.servicios.anuncios.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Notificador implements Serializable {

	
	
	
	


	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="idnotificador")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;

	
	@Column (name="password")
	private String password;
	
	@Column(name="pais")
	private String pais;
	
	@Column (name="edad")
	private int edad;
	
	
	



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
	
	
}
