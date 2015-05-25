package com.drai.eventosapp2.model;

/**
 *  Clase para el tranporte de datos de Cursos en el sistema
 * @author Heinner Esteban Alvarez Rivas <exteban34@gmail.com>
 * @version 1.0 21/05/2015
 */
public class CursoObj {
	private String id;
	private String titulo;
	private String lugar;
	private String sitioWeb;
	private String fechaInicio;
	private String horaInicio;
	private String descripcion;
	private String fechaFinalizacion;
	private String horaFinalizacion;
	
	/**
	 * Constructor de la clase EventoObj
	 * @param id del evento
	 * @param titulo del evento
	 * @param lugar donde se realiza el evento
	 * @param sitioWeb del evento
	 * @param fechaInicio del evento
	 * @param horaInicio del evento
	 * @param descripcion del evento
	 * @param fechaFinalizacion del evento
	 */
	public CursoObj(String id, String titulo, String lugar, String sitioWeb,
			String fechaInicio, String horaInicio, String descripcion,
			String fechaFinalizacion) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.lugar = lugar;
		this.sitioWeb = sitioWeb;
		this.fechaInicio = fechaInicio;
		this.horaInicio = horaInicio;
		this.descripcion = descripcion;
		this.fechaFinalizacion = fechaFinalizacion;
	}
	
	/**
	 * Getters y Setters de los valores de la clase CursoObj
	 * 
	 */
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getSitioWeb() {
		return sitioWeb;
	}
	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(String fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	public String getHoraFinalizacion() {
		return horaFinalizacion;
	}
	public void setHoraFinalizacion(String horaFinalizacion) {
		this.horaFinalizacion = horaFinalizacion;
	}
	
	
	
}
