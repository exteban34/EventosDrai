package com.drai.eventosapp2;


import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import com.drai.eventosapp2.R;
import com.drai.eventosapp2.util.LeerJSON;

/**
 * 
 * Clase encargada de consultar un expositor y mostrarlo al usuario.
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @version 1.0 23/02/2015
 */
public class Expositor extends Activity{

	/**
	 * Int con el id del expositor a consultar
	 */
	int id;
	/**
	 * ProgressDialog que indica la carga de datos
	 */
	ProgressDialog pDialog;
	/**
	 * TextView's de la vista
	 */
	TextView textViewNombreExpositor;
	TextView textViewCorreoExpositor;	
	TextView textViewDescripcionExpositor; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.expositor_vista);
		id= getIntent().getIntExtra("id", 0);
		textViewNombreExpositor= (TextView) findViewById(R.id.nombreExpositorTV);
		textViewCorreoExpositor= (TextView) findViewById(R.id.correoExpositorTV);		
		textViewDescripcionExpositor= (TextView) findViewById(R.id.textViewDescripcionExpo);
		new LeerJSONTaskExpositor().execute(
  				 "http://ingenieria.udea.edu.co:8080/EventoWS/resources/expositor/"+id);
		
	}
	
	/**
	 * Clase Asincrona encargada de consultar el objeto JSON y desplegar el resultado en la vista
	 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
	 * @version 1.0 23/02/2015
	 */	
	private class LeerJSONTaskExpositor extends AsyncTask<String, Void, String> {
		
		protected void onPreExecute() {
			pDialog = new ProgressDialog(Expositor.this);
			pDialog.setMessage(getString(R.string.carga_expositor));
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
			 		JSONObject jsonObj= new JSONObject(result);
			 		textViewNombreExpositor.setText(jsonObj.getString("nombre"));
			 		textViewCorreoExpositor.setText(jsonObj.getString("correo"));			 	
			 		textViewDescripcionExpositor.setText(jsonObj.getString("descripcion"));
			 
			 	} catch (Exception e) {
			 		e.printStackTrace();
			 	}
			 }
	
	}
	
}
