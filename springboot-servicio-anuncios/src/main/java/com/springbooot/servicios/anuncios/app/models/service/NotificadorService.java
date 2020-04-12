package com.springbooot.servicios.anuncios.app.models.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
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

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/notificador")
@CrossOrigin
public class NotificadorService {

	
	@Autowired
    NotificadorDao notificadordao;
	
    private EmitterProcessor<Notificador> notificationProcessor;
    private static List<Notificador> listaNotificadores = new ArrayList<>();
    
    @PostConstruct
    private void createProcessor() {
        notificationProcessor = EmitterProcessor.<Notificador>create();
    }
    
    
    
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
  @RequestMapping(
          path = "/save",
          method = RequestMethod.POST)
  public ResponseEntity<?> creat(
          @RequestBody Notificador entityParam) {
	  Notificador temp = notificadordao.save(entityParam);
		
				
		
      listaNotificadores.add(entityParam);

      // cuando se crea una nueva persona.... notificar esta accion al emisor
      System.out.println("Notificando nueva persona:" + entityParam.getNombre());
      notificationProcessor.onNext(entityParam);

      return new ResponseEntity<>(entityParam, HttpStatus.OK);
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
     
     
     
     
     private Flux<ServerSentEvent<Notificador>> getNotificadorSSE() {

         // notification processor retorna un FLUX en el cual podemos estar "suscritos" cuando este tenga otro valor ...
         return notificationProcessor
                 .log().map(
                         (notificador) -> {
                             System.out.println("Sending Notificador:" + notificador.getId());
                             return ServerSentEvent.<Notificador>builder()
                                     .id(UUID.randomUUID().toString())
                                     .event("notificador-result")
                                     .data(notificador)
                                     .build();
                         }).concatWith(Flux.never());
     }
     
     
     
     private Flux<ServerSentEvent<Notificador>> getNotificationHeartbeat() {
         return Flux.interval(Duration.ofSeconds(10))
                 .map(i -> {
                     System.out.println(String.format("sending heartbeat [%s] ...", i.toString()));
                     return ServerSentEvent.<Notificador>builder()
                             .id(String.valueOf(i))
                             .event("heartbeat-result")
                             .data(null)
                             .build();
                 });
     }     

    		 
     @GetMapping(
             value = "/notification/sse"
     )
     public Flux<ServerSentEvent<Notificador>>
             getJobResultNotification() {

         return Flux.merge(
                 getNotificationHeartbeat(),
                 getNotificadorSSE()
         );

     }
     /* -------------------------------------------------------------------------------------------------------------- */
	 
    		 
    		 
    		 
    		 
}


























