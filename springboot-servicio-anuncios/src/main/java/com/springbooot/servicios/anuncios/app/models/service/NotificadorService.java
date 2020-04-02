package com.springbooot.servicios.anuncios.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.springbooot.servicios.anuncios.app.models.dao.NotificadorDao;
import com.springbooot.servicios.anuncios.app.models.entity.Notificador;

@RestController
@RequestMapping("/notificador")
@CrossOrigin
public class NotificadorService {

	
	@Autowired
    NotificadorDao notificadordao;
	
	
	
 //Servicio de Mostrar Lista Completa
  @RequestMapping(value= "/all",method =RequestMethod.GET)
	  
  public List<Notificador> getAll(){
	  return notificadordao.findAll();
  }
	
  //Servicio de Mostrar Por ID
  @GetMapping (path = {"/{idnotificador}"})
	public Notificador findById(@PathVariable("idnotificador") int idnotificador){
		
		return notificadordao.findById(idnotificador).get();
		
	}
  
  
  
  //Servicio Para Crear Notificador
  	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Notificador saveNotificador(@RequestBody Notificador notificador) {
		
		
		
		Notificador temp = notificadordao.save(notificador);
	
		return temp;		
	}
  
  //Servicio Para Eliminar Notificador
  	
  	@DeleteMapping(path = {"/{idnotificador}"})
	public void deleteItemById(@PathVariable("idnotificador")int itemId) {
		notificadordao.delete(notificadordao.findById(itemId).get());
	}

  	
  	
  	//Servicio Para Update Notificador
    @PutMapping("/{idnotificador}")
	  Notificador updateNotificador(@RequestBody Notificador  notificador, @PathVariable ("idnotificador") int idnotificador) {

	    return notificadordao.findById(idnotificador)
	      .map(notificadores -> {
	    
	    	  notificadores.setNombre(notificador.getNombre());
	    	  notificadores.setApellido(notificador.getApellido());
	    	  notificadores.setPassword(notificador.getPassword());
	    	  notificadores.setPais(notificador.getPais());
	    	  notificadores.setEdad(notificador.getEdad());
	    	  return notificadordao.save(notificadores);
	      })
	      .orElseGet(() -> {
	      notificador.setId(idnotificador);
	        return notificadordao.save(notificador);
	        
	      });
	  }
	  
    
    //Servicio Para Buscar Por Nombre
    @GetMapping("/find/by/nombre/{nombre}")
    
    public List<Notificador> findByNombre(@PathVariable ("nombre") String nombre){
    	return notificadordao.findByNombre(nombre);
    	
    	
    	

    }
    
    
  //Servicio Para Buscar Por Apellido
    @GetMapping("/find/by/apellido/{apellido}")
    public List<Notificador> findByApellidoContains(@PathVariable ("apellido") String apellido){
    	return notificadordao.findByApellidoContains(apellido);
    
    }
    
    
    //Servicio Para Buscar Por Password
     @GetMapping("/find/by/password/{password}")
     public List<Notificador> findByPasswordContains(@PathVariable ("password") String password){
     return notificadordao.findByPasswordContains(password);
    
}
     
     //Servicio Para Buscar Por Pais
     @GetMapping("/find/by/pais/{pais}")
     public List<Notificador> findByPais(@PathVariable ("pais") String pais){
    	 return notificadordao.findByPais(pais);
     }
     
     
     //Servicio Para Buscar Por Edad
     @GetMapping("/find/by/edad/{edad}")
     public List<Notificador> findByEdad(@PathVariable ("edad") int edad){
    	 return notificadordao.findByEdad(edad);
    	 
     }
     
     
     //Servicio Para Mostrar Por Id}
     @GetMapping("/find/by/id/{id}")
 	public Notificador findById2(@PathVariable("id") int id){
 		
 		return notificadordao.findById(id).get();
 		
 	}	
     
     @GetMapping("/find/by/nombre/{nombre}/password/{password}")

 	public List<Notificador> findByNombreAndPassword(@PathVariable("nombre") String nombre, @PathVariable("password") String password){
 		
 		return notificadordao.findByNombreAndPassword(nombre, password);
 		
 	}	
}




























