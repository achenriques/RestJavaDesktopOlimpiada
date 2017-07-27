package com.example.TareaRest;

public class SedeJJOO {

	private int ano, id_tipo_jjoo, sede;
	private String ciudad, epoca;
	
	public SedeJJOO (int a, int s, String c, int id, String e)
	{
		ano =a;
		sede = s;
		ciudad = c;
		id_tipo_jjoo = id;
		epoca = e;
	}
	
	public int getAno() {
		return ano;
	}
	
	public int getId_tipo_jjoo() {
		return id_tipo_jjoo;
	}
	
	public int getSede() {
		return sede;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public String getEpoca() {
		return epoca;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public void setId_tipo_jjoo(int id_tipo_jjoo) {
		this.id_tipo_jjoo = id_tipo_jjoo;
	}
	
	public void setSede(int sede) {
		this.sede = sede;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public void setEpoca(String epoca) {
		this.epoca = epoca;
	}
	
	@Override
	public String toString() {
		String toret = ("Año: " + ano + ",en la ciudad de: "+ ciudad +" hubo juegos de: " + epoca + "");
		return toret;
	}
	
}
