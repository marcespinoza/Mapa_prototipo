package com.mapa.Model;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.mapa.Interfaces.Mapa;
import com.mapa.Presenter.MapaPresenter;
import com.mapa.R;
import com.mapa.View.Principal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marceloi7 on 16/02/2018.
 */

public class MapaModel implements Mapa.Model {

    //----Coordenadas colonia 1 --------//
    double pointY[]={-96.72849 ,-96.7281,-96.72766,-96.72832,-96.72778,-96.72787};
    double pointX[]={17.0618,17.06173,17.06195,17.06163,17.06132,17.06097};
    //-----Coordenadas colonia 2 ------//
    double pointY2[]={-96.73839 ,-96.73886, -96.73793,-96.73771,-96.73765, -96.7377};
    double pointX2[]={17.06589, 17.06571, 17.06603, 17.06656, 17.06696, 17.06744};
    List<LatLng> points = new ArrayList<LatLng>();
    private Mapa.Presenter presenter;
    String[] coor_colonias = new String[6];

    public MapaModel(Mapa.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void listarColonias(int posicion) {
        points.clear();
        if(posicion==1){
            for (int i = 0 ; i < pointX.length; i++){
                points.add(new LatLng(pointX[i],pointY[i]));
            };

        }else if (posicion==2){
            for (int i = 0 ; i < pointX2.length; i++){
                points.add(new LatLng(pointX2[i],pointY2[i]));
            };
        }

        enviarCoordenadas(points);
    }

    @Override
    public void enviarCoordenadas(List<LatLng> coor) {
        presenter.enviarCoordenadas(coor);
    }

}
