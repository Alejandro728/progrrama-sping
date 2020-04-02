package com.springbooot.servicios.anuncios.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.springbooot.servicios.anuncios.app.models.dao.NoticiasDao;
import com.springbooot.servicios.anuncios.app.models.dao.NotificadorDao;
import com.springbooot.servicios.anuncios.app.models.entity.Noticias;
import com.springbooot.servicios.anuncios.app.models.entity.Notificador;






@RestController
@RequestMapping("/noticias")
public class NoticiasService {

	
	@Autowired
    NoticiasDao noticiasdao;

	@Autowired
	NotificadorDao notificadoresdao;
	
	
	//Servicio de Mostrar Lista Completa
	  @RequestMapping(value= "/all",method =RequestMethod.GET)
		  
	  public List<Noticias> getAll(){
		  return noticiasdao.findAll();
	  }
	  
	//Servicio de Mostrar Por ID
	  @GetMapping (path = {"/{idnoticias}"})
		public Noticias findById(@PathVariable("idnoticias") int idnoticias){
			
			return noticiasdao.findById(idnoticias).get();
			
		}
	  
	  
	  
	//Servicio Para Crear Noticias
	  	@RequestMapping(value="/save",method=RequestMethod.POST)
		public Noticias saveNoticias(@RequestBody Noticias noticias) {
			
	  		
	  		Noticias temp = noticiasdao.save(noticias);
			
			
			
			return temp;
			
				
		}
	  	
	  	
	  	@GetMapping("/find/by/idnotificador/{idnotificador}")
	 	public List<Noticias> findByIdNotificador(@PathVariable("idnotificador") int idnotificador){
	 		
	 		return noticiasdao.findByIdnotificador(idnotificador);
	 		
	 	}	
	  	
	  
	  
}
