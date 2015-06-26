package com.drai.eventosapp2;



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
 * Clase encargada de mostrar los expositores al usuario.
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @version 1.0 23/02/2015
 */
public class ListaExpositores extends Activity{
	/**
	 * Lista para almacenar los expositores a desplegar
	 */
	String[] lista;
	
	/**
	 *ListView controladora 
	 */
	private ListView listview;
	
	/**
	 * Arreglo con los id's de los expositores
	 */
	int[] idsEx;
		
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.lista_expositores);
		 lista=getIntent().getStringArrayExtra("expositores");	
		 idsEx=getIntent().getIntArrayExtra("ids");
		 listview=(ListView) findViewById(R.id.listViewExpositores);
		 listview.setAdapter(new ArrayAdapter<String>(this,
				 android.R.layout.simple_list_item_1, lista));
		 
		 listview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> pariente, View view,
						int posicion, long id) {				
											
					Intent i = new Intent("com.drai.eventosapp2.Expositor");
					i.putExtra("id", idsEx[posicion]);
					startActivityForResult(i,1);

				}
			});
		  	  		 		  
		 }	
	
	
}


