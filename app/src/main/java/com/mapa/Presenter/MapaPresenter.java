package com.mapa.Presenter;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.mapa.Interfaces.Mapa;
import com.mapa.Model.MapaModel;
import com.mapa.View.Principal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marceloi7 on 16/02/2018.
 */

public class MapaPresenter implements Mapa.Presenter{

    private Mapa.VistaMapa view;
    private Mapa.Model model;
    List<LatLng> colonia1 = new ArrayList<LatLng>();
    List<LatLng> colonia2 = new ArrayList<LatLng>();

    public MapaPresenter(Mapa.VistaMapa view){
        this.view=view;
        model = new MapaModel(this);
    }

    @Override
    public void mostrarColonias(int posicion) {

        model.listarColonias(posicion);
    }

    @Override
    public void enviarCoordenadas(List<LatLng> coor) {
        view.enviarCoordenadas(coor);
    }
}
