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
import android.widget.TextView;
import android.widget.Toast;

import com.mapa.R;

public class Marcador extends Fragment {

    private String info;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.info_window_form_fragment, container, false);
        TextView info = view.findViewById(R.id.editTextFormInfoWindow);
        info.setText(getArguments().getString("info"));
        return view;
    }

    public static Marcador newInstance(String info){
        Marcador marcador = new Marcador();
        Bundle args = new Bundle();
        args.putString("info",info);
        if(info!=null){
            marcador.info=info;
        }
        marcador.setArguments(args);
        return marcador;

    }

}
