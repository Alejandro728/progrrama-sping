package com.springbooot.servicios.anuncios.app.models.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.springbooot.servicios.anuncios.app.models.entity.Notificador;

public interface NotificadorDao extends JpaRepository<Notificador, Serializable>{

	List<Notificador> findByNombre(String nombre);
	List<Notificador> findByApellidoContains(String apellido);
	List<Notificador> findByPasswordContains(String password);
	List<Notificador> findByPais(String pais);
	List<Notificador> findByEdad(int edad);
	List<Notificador> findByNombreAndPassword(String nombre, String password);
	
}
