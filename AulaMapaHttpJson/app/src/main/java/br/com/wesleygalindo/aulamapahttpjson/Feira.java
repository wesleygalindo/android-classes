package br.com.wesleygalindo.aulamapahttpjson;

import java.io.Serializable;

public class Feira implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5008413760227898116L;

	private String latitude;
	private String longitude;
	private String nome;
	private String horario;
	
	public Feira() {
		
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

}
