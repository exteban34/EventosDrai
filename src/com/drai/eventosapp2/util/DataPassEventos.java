package com.drai.eventosapp2.util;

import java.io.Serializable;
import java.util.ArrayList;

import com.drai.eventosapp2.model.EventoObj;


public class DataPassEventos implements Serializable {

	private ArrayList<EventoObj> eventos;

	   public DataPassEventos(ArrayList<EventoObj> data) {
	      this.eventos = data;
	   }

	   public ArrayList<EventoObj> getEventos() {
	      return this.eventos;
	   }
}
