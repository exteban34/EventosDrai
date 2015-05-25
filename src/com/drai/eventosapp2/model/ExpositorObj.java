package com.drai.eventosapp2.model;


/**
 *  Clase para el tranporte de datos de Expositores en el sistema
 * @author Heinner Esteban Alvarez Rivas <exteban34@gmail.com>
 * @version 1.0 21/05/2015
 */
public class ExpositorObj {

	private String id;
	private String nombre;
	private String correo;
	private String descripcion;
	
	/**
	 * Constructor de la clase EventoObj
	 * @param id del expositor
	 * @param nombre del expositor
	 * @param correo del expositor
	 * @param descripcion del expositor
	 */
	public ExpositorObj(String id, String nombre, String correo,
			String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.descripcion = descripcion;
	}

	
	/**
	 * Getters y Setters de los atributos de la clase ExpositorObj
	 * 
	 */

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}
