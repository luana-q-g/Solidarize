package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioEvento;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroEvento;
import com.projetointegrador.solidarize.VIEW.EsqueciSenha;

import java.util.ArrayList;

public class AdapterListaPerfilEventos extends FirebaseListAdapter<CadastroUsuarioEvento> {
    public AdapterListaPerfilEventos(FirebaseListOptions options){
        super (options);
    }

    protected void populateView (View v, CadastroUsuarioEvento e, int position){
        TextView lbl_nome;
        TextView lbl_id;

        lbl_nome= v.findViewById(R.id.lbl_nome_evento);
        lbl_id= v.findViewById(R.id.lbl_id_evento);

        lbl_nome.setText(e.getNomeEvento());
        lbl_id.setText(e.getIdEvento());
    }
}