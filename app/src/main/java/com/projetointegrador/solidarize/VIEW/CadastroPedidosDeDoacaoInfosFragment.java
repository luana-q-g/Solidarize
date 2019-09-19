package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroPedidosDeDoacaoInfosFragment extends Fragment {
    private EditText txt_item_pedido;
    private EditText txt_meta_qtd;
    private EditText txt_tipo_pedido;
    private EditText txt_dt_validade_pedido;
    private EditText txt_nivel_urgencia;

    private Button btn_continuar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_act_cadastro_pedidos_de_doacao_infos, container, false);

        txt_item_pedido= view.findViewById(R.id.txt_nome_pedido);
        txt_meta_qtd= view.findViewById(R.id.txt_meta_qtd);
        txt_tipo_pedido= view.findViewById(R.id.txt_tipo_pedido);
        txt_dt_validade_pedido= view.findViewById(R.id.txt_dt_final);
        txt_nivel_urgencia= view.findViewById(R.id.txt_nivel_urgencia);
        btn_continuar= view.findViewById(R.id.btn_continuar_infos_eventos);

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item_pedido, meta, tipo, dt_validade, nivel_urgencia;

                item_pedido= txt_item_pedido.getText().toString();
                meta= txt_meta_qtd.getText().toString();
                tipo= txt_tipo_pedido.getText().toString();
                dt_validade= txt_dt_validade_pedido.getText().toString();
                nivel_urgencia= txt_nivel_urgencia.getText().toString();

                CadastroPedidosDeDoacao act= (CadastroPedidosDeDoacao) getActivity();

                act.setInfos(item_pedido, meta, tipo, dt_validade, nivel_urgencia);

                FragmentManager fm= act.getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                CadastroPedidosDeDoacaoEnderecoefimFragment cadastro_enderecoefim= new CadastroPedidosDeDoacaoEnderecoefimFragment();
                ft.replace(R.id.place_holder_info_cadastro_pedidos_de_doacao, cadastro_enderecoefim);
                ft.commit();
            }
        });

        return view;
    }
}
