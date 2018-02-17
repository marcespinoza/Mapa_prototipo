package com.mapa.View;

/**
 * Created by Marceloi7 on 15/02/2018.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mapa.R;

public class Marcador extends Fragment {

    private String info;
    private ImageView imagen;
    private TextView informacion;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.info_window_form_fragment, container, false);
        informacion = view.findViewById(R.id.editTextFormInfoWindow);
        imagen = view.findViewById(R.id.roundedImageView);
        informacion.setText(getArguments().getString("info"));
        asignarImagen(getArguments().getInt("imagen"));
        return view;
    }

    void asignarImagen(int id){
        switch (id){
            case 0:imagen.setImageResource(R.drawable.poste1);break;
            case 1:imagen.setImageResource(R.drawable.poste2);break;
            case 2:imagen.setImageResource(R.drawable.poste3);break;
            case 3:imagen.setImageResource(R.drawable.poste4);break;
            case 4:imagen.setImageResource(R.drawable.poste5);break;
            case 5:imagen.setImageResource(R.drawable.poste6);break;

        }
    }

    public static Marcador newInstance(String info, int imagen){
        Marcador marcador = new Marcador();
        Bundle args = new Bundle();
        args.putString("info",info);
        args.putInt("imagen",imagen);
        if(info!=null){
            marcador.info=info;
        }
        marcador.setArguments(args);
        return marcador;

    }

}
