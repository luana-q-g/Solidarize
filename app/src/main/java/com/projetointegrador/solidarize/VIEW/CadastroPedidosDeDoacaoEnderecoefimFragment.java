package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.DAO.PedidosDeDoacaoDAO;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroPedidosDeDoacaoEnderecoefimFragment extends Fragment {
    private Spinner txt_estado;
    private Spinner txt_cidade;
    private EditText txt_rua;
    private EditText txt_complemento;

    private Button btn_voltar;
    private Button btn_cadastrar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_act_cadastro_evento_enderecoefim, container, false);

        txt_estado= view.findViewById(R.id.spin_estados);
        txt_cidade= view.findViewById(R.id.spin_cidades);
        txt_rua= view.findViewById(R.id.txt_rua);
        txt_complemento= view.findViewById(R.id.txt_complemento);
        btn_voltar= view.findViewById(R.id.btn_voltar_enderecoefim_pedidos_doacao);
        btn_cadastrar= view.findViewById(R.id.btn_cadastrar_enderecoefim_pedidos_doacao);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e, c, r, com;
                //e= txt_estado.getSelectedItem().toString();
                //c= txt_cidade.getSelectedItem().toString();
                e= "";
                c= "";
                r= txt_rua.getText().toString();
                com= txt_complemento.getText().toString();

                //cadastro
                CadastroPedidosDeDoacao cadastro= (CadastroPedidosDeDoacao) getActivity();
                PedidoDeDoacao pedido= cadastro.getPedido_de_doacao();

                pedido.setEstado(e);
                pedido.setCidade(c);
                pedido.setRua(r);
                pedido.setComplemento(com);

                //colocar id da instituicao que cadastra tambem!!!

                PedidosDeDoacaoDAO pedidoDao= new PedidosDeDoacaoDAO();
                pedidoDao.inserirPedidoDeDoacao(pedido);
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                CadastroPedidosDeDoacaoInfosFragment cadastro_infos= new CadastroPedidosDeDoacaoInfosFragment();
                ft.replace(R.id.place_holder_info_cadastro_pedidos_de_doacao, cadastro_infos);
                ft.commit();
            }
        });

        return view;
    }
}
