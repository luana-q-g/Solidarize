package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioPedidoDoacao;
import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.R;

import java.util.ArrayList;

public class AdapterListaPerfilPedidosDoacao extends FirebaseListAdapter<CadastroUsuarioPedidoDoacao> {
    public AdapterListaPerfilPedidosDoacao(FirebaseListOptions options){
        super (options);
    }

    protected void populateView (View v, CadastroUsuarioPedidoDoacao p, int position){
        TextView lbl_nome_pedido;
        TextView lbl_id;

        lbl_nome_pedido= v.findViewById(R.id.lbl_nome_pedido_doacao);
        lbl_id= v.findViewById(R.id.lbl_id_pedido);

        lbl_nome_pedido.setText(p.getNomePedido());
        lbl_id.setText(p.getIdPedido());
    }
}
