package com.example.TareaRest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WebPetition {

	
	public static ArrayList<Olimpiada> getOlimpiadas()
	{
		ArrayList<Olimpiada> toret = null;
		
		try { 

            URL url = new URL("http://localhost:8080/olimpiadas/"); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
            connection.setDoOutput(true); 
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("GET"); 
            connection.setRequestProperty("Content-Type", "application/json"); 
		
            toret = JsonConvert.Json2ArrayOlimpiada(connection.getInputStream()); //Devuelve array list the objetos
            	    
         } catch(Exception e) { 
             throw new RuntimeException(e); 
         }
			
		if (toret == null)
			System.out.println("Something goes wrong!");
		
		return toret;
	}
	
	
	public static ArrayList<SedeJJOO> getSedes()
	{
		ArrayList<SedeJJOO> toret = null;
		
		try { 

            URL url = new URL("http://localhost:8080/sedes/"); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
            connection.setDoOutput(true); 
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("GET"); 
            connection.setRequestProperty("Content-Type", "application/json"); 
		
            toret = JsonConvert.Json2ArraySede(connection.getInputStream()); //Devuelve array list the objetos
            	    
         } catch(Exception e) { 
             throw new RuntimeException(e); 
         }
			
		if (toret == null)
			System.out.println("Something goes wrong!");
		
		return toret;
	}
	
	public static ArrayList<ArrayList<String>> getPaises()
	{
		ArrayList<ArrayList<String>> toret = new ArrayList<>();
		
		try { 

            URL url = new URL("http://localhost:8080/paises/"); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
            connection.setDoOutput(true); 
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("GET"); 
            connection.setRequestProperty("Content-Type", "application/json"); 
		
            toret = JsonConvert.Json2ArrayPais(connection.getInputStream()); //Devuelve array list the objetos
            	    
         } catch(Exception e) { 
             throw new RuntimeException(e); 
         }
			
		if (toret == null)
			System.out.println("Something goes wrong!");
		
		return toret;
	}
	
	public static boolean newSede(int ano, int sed, int id_tipo)
	{
		boolean toret = false;
		
		try { 

            URL url = new URL("http://localhost:8080/sedes/" + ano + "/" + sed + "/" + id_tipo + "/"); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
            connection.setDoOutput(true); 
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("POST"); 
            connection.setRequestProperty("Content-Type", "application/json");
            
            if (connection.getResponseCode() == 200)
            	toret = true;
            else
            	toret = false;
            	    
         } catch(Exception e) { 
             throw new RuntimeException(e); 
         }
			
		if (toret == false)
			System.out.println("Something goes wrong!");
		
		return toret;
	}
	
	public static boolean modifySede(int anoViejo, int anoNuevo, int tipo)
	{
		boolean toret;
		
		try { 

            URL url = new URL("http://localhost:8080/sedes/" + anoViejo + "/" + anoNuevo + "/" + tipo + "/"); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
            connection.setDoOutput(true); 
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("PUT"); 
            connection.setRequestProperty("Content-Type", "application/json"); 
		
            if (connection.getResponseCode() == 200)
            	toret = true;
            else
            	toret = false;
            	    
         } catch(Exception e) { 
             throw new RuntimeException(e); 
         }
			
		if (toret == false)
			System.out.println("Something goes wrong!");
		
		return toret;
	}
	
	
	public static boolean deleteSede(int ano)
	{
		boolean toret = false;
		
		try { 

            URL url = new URL("http://localhost:8080/sedes/" + ano +"/"); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
            connection.setDoOutput(true); 
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("DELETE"); 
            //connection.setRequestProperty("Content-Type", "application/json"); 
            	    
            if (connection.getResponseCode() == 200)
            	toret = true;
            else
            	toret = false;
            	    
         } catch(Exception e) { 
             throw new RuntimeException(e); 
         }
			
		if (toret == false)
			System.out.println("Something goes wrong!");
		
		return toret;
	}
}
