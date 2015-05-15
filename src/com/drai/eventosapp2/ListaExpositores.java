package com.drai.eventosapp2;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 
 * Clase encargada de mostrar los expositores al usuario.
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @version 1.0 23/02/2015
 */
public class ListaExpositores extends ListActivity{
	/**
	 * Lista para almacenar los expositores a desplegar
	 */
	String[] lista;
	
	/**
	 * Arreglo con los id's de los expositores
	 */
	int[] idsEx;
		
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);	
		 lista=getIntent().getStringArrayExtra("expositores");	
		 idsEx=getIntent().getIntArrayExtra("ids");
		  setListAdapter(new ArrayAdapter<String>(this,
					 android.R.layout.simple_list_item_1, lista));			  
		  	  		 		  
		 }
	
	
	/**
	 * Metodo encargado de lanzar la actividad que da vista al expositor
	 */
	public void onListItemClick( ListView parent, View v, int position, long id)
			 {
					Intent i = new Intent("com.drai.eventosapp2.Expositor");
					i.putExtra("id", idsEx[position]);
					startActivityForResult(i,1);	
			 }
	
	
}


