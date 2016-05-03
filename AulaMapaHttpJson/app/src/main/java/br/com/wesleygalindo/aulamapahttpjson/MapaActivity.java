package br.com.wesleygalindo.aulamapahttpjson;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends Activity {

	private GoogleMap googleMaps;
	private ArrayList<Marker> markers;
	private ArrayList<Feira> feiras;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa);
		
		feiras = (ArrayList<Feira>) getIntent().getSerializableExtra("feiras");
		googleMaps = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		
		googleMaps.getUiSettings().setMyLocationButtonEnabled(true); // Inserir sua localização
		googleMaps.setMyLocationEnabled(true); // Botão minha localização
		
		markers = new ArrayList<Marker>();
		
		for (int i = 0; i < feiras.size(); i++) { // Inserindo Marcadores no Mapa
			double latitude = Double.parseDouble(feiras.get(i).getLatitude());
			double longitude = Double.parseDouble(feiras.get(i).getLongitude());

			// cria um marcador na posição passada
			MarkerOptions markerOpts = new MarkerOptions()
					.position(new LatLng(latitude, longitude))
					.title(feiras.get(i).getNome())
					.snippet(
							feiras.get(i).getHorario());

			// adicionando marcardor
			Marker marker = googleMaps.addMarker(markerOpts);
			markers.add(marker);
		}
		
		if (markers.size() > 1) {
			// Ajustar a camera do Maps com o zoom encima das Marcas
			googleMaps.setOnCameraChangeListener(new OnCameraChangeListener() {
	
				@Override
				public void onCameraChange(CameraPosition position) {
					LatLngBounds.Builder builder = new LatLngBounds.Builder();
					for (Marker mark : markers) {
						builder.include(mark.getPosition());
					}
					LatLngBounds bounds = builder.build();

					googleMaps.moveCamera(CameraUpdateFactory.newLatLngBounds(
							bounds, 100));
					googleMaps.setOnCameraChangeListener(null);
				}
			});

		} else {

			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(markers.get(0).getPosition(), 18);
			googleMaps.moveCamera(update);
		}
		
	}
}
