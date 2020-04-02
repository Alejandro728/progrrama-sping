package com.springbooot.servicios.anuncios.app.models.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbooot.servicios.anuncios.app.models.entity.Noticias;

public interface NoticiasDao extends JpaRepository<Noticias, Serializable> {

	
	List<Noticias> findByIdnotificador(int idnotificador);
	
}
