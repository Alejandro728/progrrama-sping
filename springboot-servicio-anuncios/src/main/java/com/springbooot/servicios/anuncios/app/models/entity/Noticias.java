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
public class Noticias implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name="idnoticias")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name="contenido")
	private String contenido;
	
	@Column(name="idnotificador")
	private int idnotificador;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	

	public int getIdnotificador() {
		return idnotificador;
	}

	public void setIdnotificador(int idnotificador) {
		this.idnotificador = idnotificador;
	}

	
	
	
	
	
}
