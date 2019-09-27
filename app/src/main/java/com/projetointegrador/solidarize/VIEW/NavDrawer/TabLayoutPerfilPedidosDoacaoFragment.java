package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.CadastroPedidosDeDoacao;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabLayoutPerfilPedidosDoacaoFragment extends Fragment {
    private TextView lbl_existencia_pedidos_doacao;
    private ListView lista_pedidos_criados;
    private Button btn_criar_pedido;

    private ArrayList<PedidoDeDoacao> pedidos= new ArrayList<>();
    private AdapterListaPerfilPedidosDoacao adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab_layout_perfil_pedidos_doacao, container, false);

        lbl_existencia_pedidos_doacao= view.findViewById(R.id.lbl_existencia_pedido_doacao);
        lista_pedidos_criados= view.findViewById(R.id.lista_pedido_doacao_criados);
        btn_criar_pedido= view.findViewById(R.id.btn_criar_pedido_doacao);

        adapter = new AdapterListaPerfilPedidosDoacao(getActivity().getApplicationContext(), pedidos);
        lista_pedidos_criados.setAdapter(adapter);

        //dados.addValueEventListener(new EscutadorFirebase());

        btn_criar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_cadastro_pedido= new Intent(getActivity().getApplicationContext(), CadastroPedidosDeDoacao.class);
                startActivity(i_cadastro_pedido);
            }
        });

        return view;
    }
}
