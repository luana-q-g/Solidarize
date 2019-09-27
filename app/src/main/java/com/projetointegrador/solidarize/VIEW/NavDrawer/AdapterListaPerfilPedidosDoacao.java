package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.R;

import java.util.ArrayList;

public class AdapterListaPerfilPedidosDoacao extends ArrayAdapter<PedidoDeDoacao> {
    private Context context;
    private ArrayList<PedidoDeDoacao> pedidos;

    public AdapterListaPerfilPedidosDoacao(Context c, ArrayList<PedidoDeDoacao> us){
        super(c, R.layout.item_edicao_pedidos_doacao, us);
        this.context= c;
        this.pedidos= us;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater li= LayoutInflater.from(parent.getContext());

        View itemView= li.inflate(R.layout.item_edicao_pedidos_doacao, parent, false);

        /*if(eventos.get(position).getTipo() == "isso"){

        }*/
        //ImageView img_topin= itemView.findViewById(R.id.img_topin);
        TextView lbl_nome_pedido= itemView.findViewById(R.id.lbl_nome_evento);

        lbl_nome_pedido.setText(pedidos.get(position).getItem());

        return itemView;
    }

}
