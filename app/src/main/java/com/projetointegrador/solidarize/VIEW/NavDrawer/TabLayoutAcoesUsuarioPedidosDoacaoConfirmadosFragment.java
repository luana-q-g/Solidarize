package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.ConfirmaPedidoDeDoacao;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabLayoutAcoesUsuarioPedidosDoacaoConfirmadosFragment extends Fragment {
    private ListView lista_pedidos_confirmados;
    private TextView lbl_existencia_pedidos;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference confirmaPedidoDeDoacao= BD.child("confirmaPedidoDeDoacao");

    private AdapterListaPedidosConfirmados adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab_layout_acoes_usuario_pedidos_doacao_confirmados, container, false);

        lista_pedidos_confirmados= view.findViewById(R.id.lista_pedidos_de_doacao_confirmados);
        lbl_existencia_pedidos= view.findViewById(R.id.lbl_existencia_pedidos);

        String id_usuario= "";
        if (UsuarioLogado.getInstance().getUsuario().getTipo_usuario().contentEquals("pessoa")) {
            Pessoa usuario_pessoa = (Pessoa) UsuarioLogado.getInstance().getUsuario();
            id_usuario= usuario_pessoa.getId();
        }
        else{
            Instituicao usuario_instituicao = (Instituicao) UsuarioLogado.getInstance().getUsuario();
            id_usuario= usuario_instituicao.getId();
        }

        //pegar nó especifico do usuario p listar eventos salvos
        DatabaseReference referencia_usuario_pedido= confirmaPedidoDeDoacao.child(id_usuario);

        FirebaseListOptions<ConfirmaPedidoDeDoacao> pedidos_confirmados_options= new FirebaseListOptions.Builder<ConfirmaPedidoDeDoacao>()
                .setLayout(R.layout.item_edicao_pedidos_doacao)
                .setQuery(referencia_usuario_pedido, ConfirmaPedidoDeDoacao.class)
                .setLifecycleOwner(this)
                .build();

        adapter= new AdapterListaPedidosConfirmados(pedidos_confirmados_options, lbl_existencia_pedidos);

        lista_pedidos_confirmados.setAdapter(adapter);

        return view;
    }

    public class AdapterListaPedidosConfirmados extends FirebaseListAdapter<ConfirmaPedidoDeDoacao> {
        private TextView lbl_existencia_pedidos;

        public AdapterListaPedidosConfirmados (FirebaseListOptions options, TextView lbl_existencia_pedidos){
            super (options);
            this.lbl_existencia_pedidos= lbl_existencia_pedidos;
        }

        protected void populateView (View v, ConfirmaPedidoDeDoacao p, int position){
            TextView lbl_nome_pedido;
            TextView lbl_id_pedido;

            lbl_nome_pedido= v.findViewById(R.id.lbl_nome_pedido_doacao);
            lbl_id_pedido= v.findViewById(R.id.lbl_id_pedido);

            lbl_nome_pedido.setText(p.getNomePedido());
            lbl_id_pedido.setText(p.getIdPedido());
        }

        @Override
        public void onDataChanged(){
            //se chamar esse método, esvazia
            if(getCount()==0){
                lbl_existencia_pedidos.setText("você não confirmou nenhum pedido de doação...");
            }
            else{
                lbl_existencia_pedidos.setText("");
            }
        }
    }
}
