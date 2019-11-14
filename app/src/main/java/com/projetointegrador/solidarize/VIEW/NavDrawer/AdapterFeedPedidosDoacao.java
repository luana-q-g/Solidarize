package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.R;

public class AdapterFeedPedidosDoacao extends FirebaseListAdapter<PedidoDeDoacao> {
    public AdapterFeedPedidosDoacao(FirebaseListOptions options){
        super (options);
    }

    protected void populateView (View v, PedidoDeDoacao p, int position){
        TextView lbl_item, lbl_instituicao, txt_id_pedido;

        lbl_item= v.findViewById(R.id.lbl_nome_pedido_doacao);
        lbl_instituicao= v.findViewById(R.id.lbl_nome_instituicao);
        txt_id_pedido= v.findViewById(R.id.txt_id_pedido);

        lbl_item.setText(p.getItem());
        lbl_instituicao.setText(p.getEmailUsuario());
        txt_id_pedido.setText(p.getId());
    }
}
