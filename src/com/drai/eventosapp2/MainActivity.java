package com.drai.eventosapp2;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;








import com.drai.eventosapp2.R;
import com.drai.eventosapp2.model.EventoObj;
import com.drai.eventosapp2.util.DataPassEventos;
import com.drai.eventosapp2.util.LeerJSON;

/**
 * 
 * Main Activity, provee las opciones de consultar eventos y expositores.
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @version 1.0 23/02/2015
 */
public class MainActivity extends Activity {

	/**
	 * Botones de la vista
	 */
	Button botonVerEventos;
	Button botonVerExpositores;
	/**
	 * ProgressDialog que indica la carga de datos
	 */
	ProgressDialog pDialog;
	
	int numeroEventos;
	int numeroExpositores;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		botonVerEventos= (Button) findViewById(R.id.btnEventos);
		botonVerEventos.setOnClickListener( controlClick);
		botonVerExpositores = (Button) findViewById(R.id.btnExpositores);
		botonVerExpositores.setOnClickListener(controlClick);					    
	}

	/***
	 * Variable encargada de controlar el evento click
	 */
	View.OnClickListener controlClick = new View.OnClickListener() {
		  public void onClick(View v) {
		      switch(v.getId()) {

		          
		       case  R.id.btnExpositores:    	
		    	   		new LeerJSONTaskListaExpositores().execute(
				   				 "http://ingenieria.udea.edu.co:8080/EventoWS/resources/expositor/from/0");
		    	   			break;		        	
		    	   
		       case R.id.btnEventos:
		    	   	   new LeerJSONListaEventos().execute(
				   				 "http://ingenieria.udea.edu.co:8080/EventoWS/resources/evento/from/0");
		    	   	   		break;		          	        
		        
		      }
		        
		   }
		 
	};
	

	/**
	 * Clase Asincrona encargada de consultar el arreglo JSON  con los eventos y lanzar la actividad que provee la visualizacion
	 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
	 * @version 1.0 23/02/2015
	 */
	private class LeerJSONListaEventos extends AsyncTask<String, Void, String> {
		protected void onPreExecute() {
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage(getString(R.string.carga_eventos));
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(false);
			pDialog.show();
		}   
		
		protected String doInBackground(String... urls) {
			return LeerJSON.leerJSON(urls[0]);
		 	}

		 protected void onPostExecute(String result) {
		 try {
			 	pDialog.dismiss();
			 	JSONObject jsonobj = new JSONObject(result);
			 	JSONArray  jsonArray = jsonobj.getJSONArray("eventos");
			 	numeroEventos=jsonArray.length();
			 	String[] lista= new String[numeroEventos];
			 	int[] ids= new int[numeroEventos];
			 	ArrayList<EventoObj> eventosObjs = new ArrayList<EventoObj>();
			 	for (int i = 0; i < jsonArray.length(); i++) {
			 		JSONObject jsonObject = jsonArray.getJSONObject(i);
			 		EventoObj evento= new EventoObj(jsonObject.getString("id"),
			 				jsonObject.getString("titulo"),
			 				jsonObject.getString("fechaInicio"),
			 				jsonObject.getString("horaInicio"));
			 		
			 		//pendiente añadir control para evnetos o cursos
			 		eventosObjs.add(evento);
			 		lista[i]=jsonObject.getString("titulo");
			 		ids[i]=jsonObject.getInt("id");		 		
			 				 		
			 		} 
			 	Intent e = new Intent("com.drai.eventosapp2.ListaEventos");
			 	e.putExtra("eventosObj", new DataPassEventos(eventosObjs));
	    	    e.putExtra("eventos", lista);
	    	    e.putExtra("ids", ids);
	        	startActivityForResult(e,1);
			 	
			 	
			 	} catch (Exception e) {
			 		e.printStackTrace();
			 	}
			 }
	}

	/**
	 * Clase Asincrona encargada de consultar el arreglo JSON  con los expositores y lanzar la actividad que provee la visualizacion
	 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
	 * @version 1.0 23/02/2015
	 */
	private class LeerJSONTaskListaExpositores extends AsyncTask<String, Void, String> {
		protected void onPreExecute() {
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage(getString(R.string.carga_expositores));
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(false);
			pDialog.show();
		}   
		protected String doInBackground(String... urls) {
			return LeerJSON.leerJSON(urls[0]);
		 	}

		 protected void onPostExecute(String result) {
		 try {
			 	pDialog.dismiss();
			 	JSONObject jsonobj = new JSONObject(result);
			 	JSONArray  jsonArray = jsonobj.getJSONArray("expositores");
			 	numeroExpositores=jsonArray.length();
			 	String[] lista= new String[numeroExpositores];
			 	int[] ids= new int[numeroExpositores];
			 	for (int i = 0; i < jsonArray.length(); i++) {
			 		JSONObject jsonObject = jsonArray.getJSONObject(i);
			 		lista[i]=jsonObject.getString("nombre");
			 		ids[i]=jsonObject.getInt("id");		 		
			 				 		
			 		} 
			 	Intent e = new Intent("com.drai.eventosapp2.ListaExpositores");
	    	    e.putExtra("expositores", lista);
	    	    e.putExtra("ids", ids);
	        	startActivityForResult(e,1);
			 	
			 	
			 	} catch (Exception e) {
			 		e.printStackTrace();
			 	}
			 }
	}
}
