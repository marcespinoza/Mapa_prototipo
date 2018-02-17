package com.mapa.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.appolica.interactiveinfowindow.InfoWindow;
import com.appolica.interactiveinfowindow.InfoWindowManager;
import com.appolica.interactiveinfowindow.fragment.MapInfoWindowFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mapa.Interfaces.Mapa;
import com.mapa.Presenter.MapaPresenter;
import com.mapa.R;

import java.util.ArrayList;
import java.util.List;


public class Principal  extends AppCompatActivity implements OnMapReadyCallback, InfoWindowManager.WindowShowListener,
        GoogleMap.OnMarkerClickListener, Mapa.VistaMapa  {

    MaterialSpinner colonias;
    private InfoWindow colonia1;
    private InfoWindow colonia2;
    private InfoWindowManager infoWindowManager;
    private Mapa.Presenter presenter;
    private static Context context;
    private GoogleMap googleMap;
    List<InfoWindow> info_windows = new ArrayList<InfoWindow>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        MapInfoWindowFragment mapFragment = (MapInfoWindowFragment) getSupportFragmentManager()
                .findFragmentById(R.id.infoWindowMap);
        infoWindowManager = mapFragment.infoWindowManager();
        infoWindowManager.setHideOnFling(true);
        context = getApplicationContext();
        mapFragment.getMapAsync(this);
        presenter = new MapaPresenter(this);
        colonias = findViewById(R.id.spinner);
        colonias.setItems("Seleccione colonia","Colonia 1", "Colonia 2");
        colonias.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                presenter.mostrarColonias(position);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMarkerClickListener(this);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(17.064915,-96.729255),13));
    }

    @Override
    public void onWindowShowStarted(@NonNull InfoWindow infoWindow) {

    }

    @Override
    public void onWindowShown(@NonNull InfoWindow infoWindow) {

    }

    @Override
    public void onWindowHideStarted(@NonNull InfoWindow infoWindow) {

    }

    @Override
    public void onWindowHidden(@NonNull InfoWindow infoWindow) {

    }

    public static Context getContext() {
        return context;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        InfoWindow infoWindow = null;
        switch (marker.getSnippet()) {
            case "0":infoWindow = info_windows.get(0); break;
            case "1":infoWindow = info_windows.get(1); break;
            case "2":infoWindow = info_windows.get(2); break;
            case "3":infoWindow = info_windows.get(3); break;
            case "4":infoWindow = info_windows.get(4); break;
            case "5":infoWindow = info_windows.get(5); break;
            /*case "7":infoWindow = colonia1; break;
            case "8":infoWindow = colonia1; break;
            case "9":infoWindow = colonia1; break;
            case "10":infoWindow = colonia1; break;
            case "11":infoWindow = colonia1; break;
            case "12":infoWindow = colonia1; break;*/
        }

        if (infoWindow != null) {
            infoWindowManager.toggle(infoWindow, true);
        }

        return true;
    }


    @Override
    public void mostrarColonias(int posicion) {
    }

    @Override
    public void enviarCoordenadas(List<LatLng> coor, String[] info) {
        info_windows.clear();
        googleMap.clear();
        for (int i = 0; i < coor.size(); i++) {
            Marker marker = googleMap.addMarker(new MarkerOptions().position(coor.get(i)).snippet(String.valueOf(i)));
            InfoWindow.MarkerSpecification markerSpec = new InfoWindow.MarkerSpecification(5, 5);
            colonia1= new InfoWindow(marker, markerSpec, Marcador.newInstance(info[i],i));
            info_windows.add(i,colonia1);
        }

    }
}
