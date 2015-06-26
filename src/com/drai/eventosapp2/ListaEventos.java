package com.drai.eventosapp2;



import java.util.ArrayList;

import com.drai.eventosapp2.model.EventoObj;
import com.drai.eventosapp2.util.DataPassEventos;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * Clase encargada de mostrar los eventos al usuario.
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @version 1.0 23/02/2015
 */
public class ListaEventos extends Activity{
	
	/**
	 * Lista para almacenar los eventos a desplegar
	 */
	String[] lista;
	
	/**
	 *ListView controladora 
	 */
	private ListView listview;
	
	/**
	 * Arreglo con los id's de los eventos
	 */
	int[] idsEv;
	
	ArrayList<EventoObj> eventos;
	
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);	
		 setContentView(R.layout.lista_eventos);
		 lista=getIntent().getStringArrayExtra("eventos");	
		 idsEv=getIntent().getIntArrayExtra("ids");
		 DataPassEventos dpe = (DataPassEventos) getIntent().getSerializableExtra("eventosObj");
		 eventos = dpe.getEventos();
		 listview = (ListView) findViewById(R.id.listViewEventos);
		 listview.setAdapter(new ArrayAdapter<String>(this,
					 android.R.layout.simple_list_item_1, lista));
		 
		  //setListAdapter(new ArrayAdapter<String>(this,
			//		 android.R.layout.simple_list_item_1, lista));	
		 
		 
			
		 listview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> pariente, View view,
						int posicion, long id) {				
											
					Intent i = new Intent("com.drai.eventosapp2.Evento");
					i.putExtra("id", idsEv[posicion]);
					startActivityForResult(i,1);

				}
			});
		
	}
}


