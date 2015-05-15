package com.drai.eventosapp2;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 
 * Clase encargada de mostrar los eventos al usuario.
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @version 1.0 23/02/2015
 */
public class ListaEventos extends ListActivity{
	
	/**
	 * Lista para almacenar los eventos a desplegar
	 */
	String[] lista;
	
	/**
	 * Arreglo con los id's de los eventos
	 */
	int[] idsEv;
	
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);	
		 lista=getIntent().getStringArrayExtra("eventos");	
		 idsEv=getIntent().getIntArrayExtra("ids");
		  setListAdapter(new ArrayAdapter<String>(this,
					 android.R.layout.simple_list_item_1, lista));			  
		  	  		 		  
		 }
	
	/**
	 * Metodo encargado de lanzar la actividad que da vista al evento
	 */
	public void onListItemClick( ListView parent, View v, int position, long id)
			 {
					Intent i = new Intent("com.drai.eventosapp2.Evento");
					i.putExtra("id", idsEv[position]);
					startActivityForResult(i,1);	
			 }
	
	
}


