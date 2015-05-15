package com.drai.eventosapp2;

import org.json.JSONObject;
import com.drai.eventosapp2.R;
import com.drai.eventosapp2.util.LeerJSON;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Clase encargada de consultar los detalles del evento y mostrarlos al usuario.
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @version 1.0 23/02/2015
 */
public class DetalleEvento extends Activity {
	/**
	 * TextView's de la vista
	 */
	TextView textTitulo;
	TextView textDetalle;
	/**
	 * ProgressDialog que indica la carga de datos
	 */
	ProgressDialog pDialog;
	/**
	 * Int con el id del evento a consultar
	 */
	int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.evento_detalle);
		id= getIntent().getIntExtra("id", 0);
		textTitulo= (TextView) findViewById(R.id.nombreEventoTV);
		textDetalle= (TextView) findViewById(R.id.detallesEvendo);
	
		new LeerJSONTaskEvento().execute(
 				 "http://ingenieria.udea.edu.co:8080/EventoWS/resources/evento/"+id);
		
	}
	
	
	/**
	 * Clase Asincrona encargada de consultar el objeto JSON y desplegar el resultado en la vista
	 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
	 * @version 1.0 23/02/2015
	 */
	private class LeerJSONTaskEvento extends AsyncTask<String, Void, String> {
		protected void onPreExecute() {
			pDialog = new ProgressDialog(DetalleEvento.this);
			pDialog.setMessage(getString(R.string.carga_detalles));
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
			 	textTitulo.setText(jsonObj.getString("titulo"));
			 	textDetalle.setText(jsonObj.getString("fechaInicio")+"\n"+
			 						jsonObj.getString("horaInicio")+"\n"+
			 						jsonObj.getString("lugar")+"\n\n"+
			 						jsonObj.getString("descripcion")+"\n\n"+
			 						jsonObj.getString("sitioWeb")+"\n"
			 						);
			 		
			 		
			 
			 	} catch (Exception e) {
			 		e.printStackTrace();
			 	}
			 }
	
	}

}
