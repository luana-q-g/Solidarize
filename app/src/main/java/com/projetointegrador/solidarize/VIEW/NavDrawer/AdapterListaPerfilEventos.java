package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.R;

import java.util.ArrayList;

public class AdapterListaPerfilEventos extends ArrayAdapter<Evento> {
    private Context context;
    private ArrayList<Evento> eventos;

    public AdapterListaPerfilEventos(Context c, ArrayList<Evento> us){
        super(c, R.layout.item_edicao_evento, us);
        this.context= c;
        this.eventos= us;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater li= LayoutInflater.from(parent.getContext());

        View itemView= li.inflate(R.layout.item_edicao_evento, parent, false);

        /*if(eventos.get(position).getTipo() == "isso"){

        }*/
        //ImageView img_topin= itemView.findViewById(R.id.img_topin);
        TextView lbl_nome_evento= itemView.findViewById(R.id.lbl_nome_evento);

        lbl_nome_evento.setText(eventos.get(position).getNome());

        return itemView;
    }

}
