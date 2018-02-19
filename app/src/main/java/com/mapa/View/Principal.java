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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
    private InfoWindow colonia;
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
            //----Carga las colonias 1 o 2--------//
                if(position==1 || position==2){
                     presenter.mostrarColonias(position);}
            //----Si selecciona la opcion "Seleccione colonia", limpia los marcadores y centra el mapa----//
                else{
                    googleMap.clear();
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(17.064915,-96.729255),14));
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMarkerClickListener(this);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(17.064915,-96.729255),14));
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

    //----De acuerdo al marcador que se toque, se cargan diferentes informaciones---//
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
        }

        if (infoWindow != null) {
            infoWindowManager.toggle(infoWindow, true);
        }

        return true;
    }


    //-----Recibe las coordenadas de la colonia1 o colonia2----//
    @Override
    public void enviarCoordenadas(List<LatLng> coor, String[] info) {
        Marker marker;
        //----Limpio la lista de informacion de la colonia cargada anteriormente---//
        info_windows.clear();
        //------Limpio todos los marcadores de la colonia que estaba cargada---//
        googleMap.clear();
        for (int i = 0; i < coor.size(); i++) {
            marker = googleMap.addMarker(new MarkerOptions().position(coor.get(i)).snippet(String.valueOf(i)));
            InfoWindow.MarkerSpecification markerSpec = new InfoWindow.MarkerSpecification(5, 5);
            /*if(info[i].indexOf("Warning")!=-1){
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_amarillo));
            }*/
            colonia= new InfoWindow(marker, markerSpec, Marcador.newInstance(info[i],i));
            info_windows.add(i,colonia);
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coor.get(3),18));
    }
}
