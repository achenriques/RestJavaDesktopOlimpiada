package com.example.TareaRest;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	Connect c = new Connect();
	
	public RestController() {
		c = new Connect();
	}
	
	 @RequestMapping(value = "/olimpiadas/", method = RequestMethod.GET)
	    public ResponseEntity<ArrayList<Olimpiada>> listAllPaises() {
		 ArrayList<Olimpiada> olimpiadas = DAO_consult.toConsult(c.getConn());
	        if(olimpiadas.isEmpty()){
	            return new ResponseEntity<ArrayList<Olimpiada>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ArrayList<Olimpiada>>(olimpiadas, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/sedes/", method = RequestMethod.GET)
	    public ResponseEntity<ArrayList<SedeJJOO>> listAllSedes() {
		 ArrayList<SedeJJOO> sedes = DAO_consult.getSJJOO(c.getConn());
	        if(sedes.isEmpty()){
	            return new ResponseEntity<ArrayList<SedeJJOO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ArrayList<SedeJJOO>>(sedes, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/paises/", method = RequestMethod.GET)
	    public ResponseEntity<ArrayList<ArrayList<String>>> selectPais() {
		 	ArrayList<ArrayList<String>> paises = new ArrayList<>();
	    	paises = DAO_consult.selectPais(c.getConn());
	        if(paises == null){
	            return new ResponseEntity<ArrayList<ArrayList<String>>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<ArrayList<ArrayList<String>>>(paises, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/sedes/{ano}/{sede}/{tipo}/", method = RequestMethod.POST)
	    public ResponseEntity<Void> createSede(@PathVariable("ano") int ano, @PathVariable("sede") int sede, @PathVariable("tipo") int tipo) {	 	
		 	
	        if (DAO_consult.insertSJJOO(c.getConn(), ano, sede, tipo)) {
	        	//HttpHeaders headers = new HttpHeaders();
		        //headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		        return new ResponseEntity<Void>(HttpStatus.OK);
	        } else {
	        	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	        }
	    }
	 
	 @RequestMapping(value = "/sedes/{anoViejo}/{anoNuevo}/{tipo}/", method = RequestMethod.PUT)
	    public ResponseEntity<Void> updateSede(@PathVariable("anoViejo") int anoViejo, @PathVariable("anoNuevo") int anoNuevo, @PathVariable("tipo") int tipo) {
	        
	        if (DAO_consult.updateSJJOO(c.getConn(), anoViejo, anoNuevo, tipo)) {
	            return new ResponseEntity<Void>(HttpStatus.OK);
	        }
	        else {
	        	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	        }
	    }
	 
	 @RequestMapping(value = "/sedes/{ano}/", method = RequestMethod.DELETE)
	    public ResponseEntity<Void> deleteSede(@PathVariable("ano") int ano) {
	        System.out.println("Fetching & Deleting User with id " + ano);
	 
	        if (DAO_consult.deleteSJJOO(c.getConn(), ano)) {
	            System.out.println("Unable to delete. Sede with id " + ano + " not found");
	            return new ResponseEntity<Void>(HttpStatus.OK);
	        }
	        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	    }
	 

}
