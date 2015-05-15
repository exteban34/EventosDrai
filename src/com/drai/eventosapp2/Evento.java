package com.drai.eventosapp2;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;

import com.drai.eventosapp2.R;
import com.drai.eventosapp2.util.LeerJSON;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * 
 * Clase encargada de consultar un evento y mostrarlo al usuario; ofrece la
 * opcion de agregar al calendario y de ver el evento en mayor detalle.
 * 
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @version 1.0 23/02/2015
 */
public class Evento extends Activity {

	/**
	 * Int con el id del evento a consultar
	 */
	int id;

	/**
	 * Elementos de la vista
	 */
	TextView texttitulo;
	TextView textfecha;
	TextView texthora;
	TextView textlugar;
	TextView textSitioWeb;
	Button bCalendar;
	Button bDetalle;

	/**
	 * ProgressDialog que indica la carga de datos
	 */
	ProgressDialog pDialog;

	/**
	 * String's para capturar los datos desde el objeto JSON
	 */
	String titulo;
	String fechaInicio;
	String horaInicio;
	String lugar;
	String descripcion;
	String fechaFinalizacion;
	String sitioWeb;

	/**
	 * Int's que se usan para añadir el evento al calendario
	 */
	int horaIni;
	int diaIni;
	int mesIni;
	int anoIni;
	int minIni;
	int horaFin;
	int diaFin;
	int mesFin;
	int anoFin;
	int minFin;

	/**
	 * String's que se usan para capturar los datos que se requieren para añadir
	 * al calendario
	 */
	String horaExactaInicio;
	String minInicio;
	String diaInicio;
	String mesInicio;
	String anoInicio;
	String diaFinal;
	String mesFinal;
	String anoFinal;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evento_vista);
		id = getIntent().getIntExtra("id", 0);
		texttitulo = (TextView) findViewById(R.id.nombreEventoTV);
		textfecha = (TextView) findViewById(R.id.fechaEventoTV);
		texthora = (TextView) findViewById(R.id.horaEventoTV);
		textlugar = (TextView) findViewById(R.id.lugarEventoTV);
		textSitioWeb = (TextView) findViewById(R.id.sitioWebEventoTV);
		textSitioWeb.setOnClickListener(controlClick);
		bCalendar = (Button) findViewById(R.id.buttonCalendar);
		bCalendar.setOnClickListener(controlClick);
		bDetalle = (Button) findViewById(R.id.btnEventos);
		bDetalle.setOnClickListener(controlClick);
		new LeeryMostrarEvento()
				.execute("http://ingenieria.udea.edu.co:8080/EventoWS/resources/evento/"
						+ id);

	}

	/**
	 * Clase Asincrona encargada de consultar el objeto JSON y desplegar el
	 * resultado en la vista
	 * 
	 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
	 * @version 1.0 23/02/2015
	 */
	private class LeeryMostrarEvento extends AsyncTask<String, Void, String> {
		protected void onPreExecute() {
			pDialog = new ProgressDialog(Evento.this);
			pDialog.setMessage(getString(R.string.carga_evento));
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
				JSONObject jsonObj = new JSONObject(result);
				titulo = jsonObj.getString("titulo");
				fechaInicio = jsonObj.getString("fechaInicio");
				horaInicio = jsonObj.getString("horaInicio");
				lugar = jsonObj.getString("lugar");
				descripcion = jsonObj.getString("descripcion");
				fechaFinalizacion = jsonObj.getString("fechaFinalizacion");
				sitioWeb = jsonObj.getString("sitioWeb");

				llenarCampos();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Clase Asincrona encargada de consultar el objeto JSON y desplegar el
	 * formulario para añadir al calendario
	 * 
	 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
	 * @version 1.0 23/02/2015
	 */
	private class LeerEventoyAñadirAlCalendario extends
			AsyncTask<String, Void, String> {
		protected void onPreExecute() {
			pDialog = new ProgressDialog(Evento.this);
			pDialog.setMessage(getString(R.string.preparar_calendario));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... urls) {
			return LeerJSON.leerJSON(urls[0]);
		}

		protected void onPostExecute(String result) {
			try {

				JSONObject jsonObj = new JSONObject(result);
				titulo = jsonObj.getString("titulo");
				fechaInicio = jsonObj.getString("fechaInicio");
				horaInicio = jsonObj.getString("horaInicio");
				lugar = jsonObj.getString("lugar");
				descripcion = jsonObj.getString("descripcion");
				fechaFinalizacion = jsonObj.getString("fechaFinalizacion");

				obtenerDatosParaCalendario();

				Calendar beginTime = Calendar.getInstance();
				beginTime.set(anoIni, mesIni, diaIni, horaIni, minIni);
				Calendar endTime = Calendar.getInstance();
				endTime.set(anoFin, mesFin, diaFin, horaFin, minFin);
				TimeZone timeZone = TimeZone.getDefault();
				Intent intent = new Intent(Intent.ACTION_INSERT)
						.setData(Events.CONTENT_URI)
						.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
								beginTime.getTimeInMillis())
						.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
								endTime.getTimeInMillis())
						.putExtra(Events.TITLE, titulo)
						.putExtra(Events.DESCRIPTION, descripcion)
						.putExtra(Events.EVENT_LOCATION, lugar)
						.putExtra(Events.EVENT_TIMEZONE, timeZone.getID());

				startActivity(intent);

				pDialog.dismiss();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Metodo encargado de rellenar la vista.
	 * 
	 */
	public void llenarCampos() {
		texttitulo.setText(titulo);
		textfecha.setText(fechaInicio);
		texthora.setText(horaInicio);
		textlugar.setText(lugar);
		textSitioWeb.setText(sitioWeb);

	}

	/**
	 * Metodo encargado de desglosar los datos obtenidos en el objeto JSON y
	 * capturarlos en Int's para añadir al calendario
	 */
	public void obtenerDatosParaCalendario() {

		/**
		 * Desglosar String's que contienen Fechas y Horas
		 */
		horaExactaInicio = horaInicio.substring(0, horaInicio.indexOf(":"));
		minInicio = horaInicio.substring(horaInicio.indexOf(":") + 1,
				horaInicio.indexOf(" "));
		diaInicio = fechaInicio.substring(0, fechaInicio.indexOf(" "));
		mesInicio = fechaInicio.substring(fechaInicio.indexOf(" ") + 1,
				fechaInicio.indexOf(" 2"));
		anoInicio = fechaInicio.substring(fechaInicio.indexOf(" 2") + 1,
				fechaInicio.length());
		diaFinal = fechaFinalizacion.substring(0,
				fechaFinalizacion.indexOf(" "));
		mesFinal = fechaFinalizacion.substring(
				fechaFinalizacion.indexOf(" ") + 1,
				fechaFinalizacion.indexOf(" 2"));
		anoFinal = fechaFinalizacion
				.substring(fechaFinalizacion.indexOf(" 2") + 1,
						fechaFinalizacion.length());
		/**
		 * Capturar en enteros los datos en los substring's resultantes
		 */
		horaIni = Integer.valueOf(horaExactaInicio);
		minIni = Integer.valueOf(minInicio);
		diaIni = Integer.valueOf(diaInicio);
		anoIni = Integer.valueOf(anoInicio);
		horaFin = horaIni + 2;
		minFin = minIni;
		diaFin = Integer.valueOf(diaFinal);
		anoFin = Integer.valueOf(anoFinal);

		/**
		 * Ciclo para chequear el mes de la Fecha Inicio
		 */
		if (mesInicio.equalsIgnoreCase("Enero")) {
			mesIni = 0;
		} else {
			if (mesInicio.equalsIgnoreCase("Febrero")) {
				mesIni = 1;
			} else {
				if (mesInicio.equalsIgnoreCase("Marzo")) {
					mesIni = 2;
				} else {
					if (mesInicio.equalsIgnoreCase("Abril")) {
						mesIni = 3;
					} else {
						if (mesInicio.equalsIgnoreCase("Mayo")) {
							mesIni = 4;
						} else {
							if (mesInicio.equalsIgnoreCase("Junio")) {
								mesIni = 5;
							} else {
								if (mesInicio.equalsIgnoreCase("Julio")) {
									mesIni = 6;
								} else {
									if (mesInicio.equalsIgnoreCase("Agosto")) {
										mesIni = 7;
									} else {
										if (mesInicio
												.equalsIgnoreCase("Septiembre")) {
											mesIni = 8;
										} else {
											if (mesInicio
													.equalsIgnoreCase("Octubre")) {
												mesIni = 9;
											} else {
												if (mesInicio
														.equalsIgnoreCase("Noviembre")) {
													mesIni = 10;
												} else {
													if (mesInicio
															.equalsIgnoreCase("Diciembre")) {
														mesIni = 11;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		/**
		 * Ciclo para chequear el mes de la Fecha Final
		 */
		if (mesFinal.equalsIgnoreCase("Enero")) {
			mesFin = 0;
		} else {
			if (mesFinal.equalsIgnoreCase("Febrero")) {
				mesFin = 1;
			} else {
				if (mesFinal.equalsIgnoreCase("Marzo")) {
					mesFin = 2;
				} else {
					if (mesFinal.equalsIgnoreCase("Abril")) {
						mesFin = 3;
					} else {
						if (mesFinal.equalsIgnoreCase("Mayo")) {
							mesFin = 4;
						} else {
							if (mesFinal.equalsIgnoreCase("Junio")) {
								mesFin = 5;
							} else {
								if (mesFinal.equalsIgnoreCase("Julio")) {
									mesFin = 6;
								} else {
									if (mesFinal.equalsIgnoreCase("Agosto")) {
										mesFin = 7;
									} else {
										if (mesFinal
												.equalsIgnoreCase("Septiembre")) {
											mesFin = 8;
										} else {
											if (mesFinal
													.equalsIgnoreCase("Octubre")) {
												mesFin = 9;
											} else {
												if (mesFinal
														.equalsIgnoreCase("Noviembre")) {
													mesFin = 10;
												} else {
													if (mesFinal
															.equalsIgnoreCase("Diciembre")) {
														mesFin = 11;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}

	/***
	 * Variable encargada de controlar el evento click
	 */
	View.OnClickListener controlClick = new View.OnClickListener() {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.buttonCalendar:
				new LeerEventoyAñadirAlCalendario()
						.execute("http://ingenieria.udea.edu.co:8080/EventoWS/resources/evento/"
								+ id);
				break;

			case R.id.btnEventos:
				Intent e = new Intent("com.drai.eventosapp2.DetalleEvento");
				e.putExtra("id", id);
				startActivityForResult(e, 1);
				break;

			case R.id.sitioWebEventoTV:
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(sitioWeb));
				startActivity(i);
				break;

			}

		}

	};

	/**
	 * Evento click para el link de la aplicaicon
	 * 
	 * @param view
	 */


}
