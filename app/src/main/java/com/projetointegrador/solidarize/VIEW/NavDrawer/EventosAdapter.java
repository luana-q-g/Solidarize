package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.R;

public class EventosAdapter extends FirebaseListAdapter<Evento> {
    public EventosAdapter(FirebaseListOptions options){
        super (options);
    }

    protected void populateView (View v, Evento e, int position){
        TextView lbl_titulo, lbl_data, lbl_hora, lbl_endereco;

        lbl_titulo= v.findViewById(R.id.lbl_titulo_evento);
        lbl_data= v.findViewById(R.id.lbl_data_evento);
        lbl_hora= v.findViewById(R.id.lbl_hora_evento);
        lbl_endereco= v.findViewById(R.id.lbl_endereco_evento);

        lbl_titulo.setText(e.getNome());
        lbl_data.setText(e.getDt_inicio());
        lbl_hora.setText(e.getHra_inicio());
        lbl_endereco.setText(e.getCidade()+", "+e.getRua());
    }
}
