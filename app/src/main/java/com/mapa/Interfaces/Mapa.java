package com.mapa.Interfaces;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marceloi7 on 16/02/2018.
 */

public interface Mapa {

    interface VistaMapa{
        void enviarCoordenadas(List<LatLng> coor, String[] info);
    }

    interface Presenter{
        void mostrarColonias(int posicion);
        void enviarCoordenadas(List<LatLng> coor, String[] info);
    }

    interface Model{
        void listarColonias(int posicion);
        void enviarCoordenadas(List<LatLng> coor, String[] info);
    }

}
