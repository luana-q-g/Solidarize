package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.R;

import java.util.ArrayList;

public class AdapterListaPerfilEventos extends FirebaseListAdapter<Evento> {
    public AdapterListaPerfilEventos(FirebaseListOptions options){
        super (options);
    }

    protected void populateView (View v, Evento e, int position){
        TextView lbl_nome;
        Button btn_editar;

        lbl_nome= v.findViewById(R.id.lbl_nome_evento);
        btn_editar= v.findViewById(R.id.btn_editar);

        lbl_nome.setText(e.getNome());

        /*btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }
}