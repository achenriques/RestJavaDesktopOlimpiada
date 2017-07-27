package com.example.TareaRest;

public class Olimpiada {

	private int id_pais;
	private String nombre_ciudad;
	private int id_ciudad;
	private String nombre_pais;
	private int valor;
	private int n_veces_sede;
	private String tipo;
	
	public Olimpiada(int idPais, String nCiudad, int idCiudad, String nPais, int value, int nSede, String type) {
		
		id_pais = idPais;
		nombre_ciudad = nCiudad;
		id_ciudad = idCiudad;
		nombre_pais = nPais;
		valor = value;
		n_veces_sede = nSede;
		tipo = type;
	}
	
	public int getId_ciudad() {
		return id_ciudad;
	}
	
	public int getId_pais() {
		return id_pais;
	}
	
	public String getNombre_pais() {
		return nombre_pais;
	}
	
	public String getNombre_ciudad() {
		return nombre_ciudad;
	}
	
	public int getN_veces_sede() {
		return n_veces_sede;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setId_ciudad(int id_ciudad) {
		this.id_ciudad = id_ciudad;
	}
	
	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}
	
	public void setN_veces_sede(int n_veces_sede) {
		this.n_veces_sede = n_veces_sede;
	}
	
	public void setNombre_ciudad(String nombre_ciudad) {
		this.nombre_ciudad = nombre_ciudad;
	}
	
	public void setNombre_pais(String nombre_pais) {
		this.nombre_pais = nombre_pais;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		
		String toret;
		
		if (n_veces_sede == 0)
		{
			toret = ("País: " + nombre_pais + ", con ID " + id_pais + ", en la ciudad de " + nombre_ciudad +
					" con valor " + valor + ", nunca ha sido sede." );
		}
		else
		{
			toret = ("País: " + nombre_pais + ", con ID " + id_pais + ", en la ciudad de " + nombre_ciudad +
				" con valor " + valor + ", ha sido sede " + n_veces_sede + " en " + tipo + "." );
		}
		return toret;
	}
	
}
