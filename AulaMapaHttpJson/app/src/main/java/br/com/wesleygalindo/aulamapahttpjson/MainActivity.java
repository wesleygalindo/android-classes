package br.com.wesleygalindo.aulamapahttpjson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private ArrayList<Feira> listaFeiras;
	private Button btnAbrirMapa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnAbrirMapa = (Button) findViewById(R.id.open);
		btnAbrirMapa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try {
					TaskDownloadJson downloadJson = new TaskDownloadJson(MainActivity.this);
					downloadJson.execute("http://dados.recife.pe.gov.br/api/action/datastore_search?resource_id=dc6b3d07-3124-453d-b11e-72364cced7aa");
					String json = downloadJson.get();
					listaFeiras = parseJSON(json);
					
					if(!listaFeiras.isEmpty()) {
						Intent it = new Intent(MainActivity.this, MapaActivity.class);
						it.putExtra("feiras", listaFeiras);
						startActivity(it);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}
	
	private ArrayList<Feira> parseJSON(String json) {
		ArrayList<Feira> feiras = new ArrayList<Feira>();
		JSONObject feirasJson;
		
		try {
			feirasJson = new JSONObject(json);

			JSONObject resultJson = feirasJson.getJSONObject("result");
			JSONArray recordsJson = resultJson.getJSONArray("records");

			for (int i = 0; i < recordsJson.length(); i++) {
				JSONObject jsonFeira = recordsJson.getJSONObject(i);
				Feira feira = new Feira();
				feira.setNome(jsonFeira.getString("\ufeffNome"));
				feira.setLongitude(jsonFeira.getString("Longitude"));
				feira.setLatitude(jsonFeira.getString("Latitude"));
				feira.setHorario(jsonFeira.getString("Horário"));
				
				feiras.add(feira);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return feiras;
	}
}
