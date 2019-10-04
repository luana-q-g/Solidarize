package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.R;

public class AdapterFeedInstituicoes extends FirebaseListAdapter<Instituicao> {
    public AdapterFeedInstituicoes(FirebaseListOptions options){
        super (options);
    }

    protected void populateView (View v, Instituicao i, int position){
        TextView lbl_titulo, lbl_nome, lbl_cidade;

        lbl_titulo= v.findViewById(R.id.lbl_titulo_instituicao);
        lbl_cidade= v.findViewById(R.id.lbl_cidade_instituicao);

        lbl_titulo.setText(i.getNome());
        lbl_cidade.setText(i.getCidade());
    }
}
