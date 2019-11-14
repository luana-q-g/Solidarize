package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.DAO.PedidosDeDoacaoDAO;
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CadastroPedidosDeDoacaoInfosFragment extends Fragment {
    //constants
    public static final String CADASTRO= "cadastro";
    public static final String EDICAO= "edicao";

    private String tipo;
    public CadastroPedidosDeDoacaoInfosFragment(String tipo){
        this.tipo= tipo;
    }

    private EditText txt_item_pedido;
    private EditText txt_meta_qtd;
    private Spinner txt_tipo_pedido;
    private EditText txt_dt_validade_pedido;
    private SeekBar bar_nivel_urgencia;

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
        bar_nivel_urgencia= view.findViewById(R.id.seek_bar_nivel_urgencia);
        btn_continuar= view.findViewById(R.id.btn_continuar_infos_pedidos_doacao);

        if(tipo.contentEquals(CADASTRO)){
            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item_pedido, meta, tipo, dt_validade;
                    int nivel_urgencia;

                    item_pedido= txt_item_pedido.getText().toString();
                    meta= txt_meta_qtd.getText().toString();
                    tipo= "";
                    //tipo= txt_tipo_pedido.getSelectedItem().toString();
                    dt_validade= txt_dt_validade_pedido.getText().toString();
                    nivel_urgencia= bar_nivel_urgencia.getProgress();

                    CadastroPedidosDeDoacao act= (CadastroPedidosDeDoacao) getActivity();

                    act.setInfos(item_pedido, meta, tipo, dt_validade, nivel_urgencia);

                    FragmentManager fm= act.getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPedidosDeDoacaoEnderecoefimFragment cadastro_enderecoefim= new CadastroPedidosDeDoacaoEnderecoefimFragment(CadastroPedidosDeDoacaoEnderecoefimFragment.CADASTRO);
                    ft.replace(R.id.place_holder_info_cadastro_pedidos_de_doacao, cadastro_enderecoefim);
                    ft.commit();
                }
            });
        }

        if(tipo.contentEquals(EDICAO)){
            final EdicaoCadastroPedidoDoacao act= (EdicaoCadastroPedidoDoacao) getActivity();
            PedidosDeDoacaoDAO pedidosDeDoacaoDAO= new PedidosDeDoacaoDAO();

            //recupera id do evento clicado
            String id_pedido_doacao= act.getPedido_de_doacao().getId();

            //recuperando nó do evento
            DatabaseReference pedido_dados= pedidosDeDoacaoDAO.getPedidoDoacaoNo(id_pedido_doacao);

            //recuperacao de dados do firebase
            pedido_dados.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if ( dataSnapshot.exists() ) {
                        PedidoDeDoacao pedidoDeDoacao= dataSnapshot.getValue(PedidoDeDoacao.class);

                        //salva evento resgatado na activity
                        act.setPedido_de_doacao(pedidoDeDoacao);

                        String item, meta, tipo, dt_val;
                        int urgencia;

                        item= pedidoDeDoacao.getItem();
                        meta= pedidoDeDoacao.getMeta_qtd();
                        tipo= pedidoDeDoacao.getTipo_pedido();
                        dt_val= pedidoDeDoacao.getDt_validade();
                        urgencia= pedidoDeDoacao.getNivel_urgencia();

                        //act.setDadosPessoais(n, e, c, t, dt); nao precisa, é so alterar os dados nos campos
                        //act.setEnderecoEFim(evento.getId(), evento.getEmail_usuario(), evento.getEstado(), evento.getCidade(), evento.getRua(), evento.getNumero(), evento.getDescricao(), evento.getMax_participantes());

                        //seta campos de edicao
                        setDadosView(item, meta, tipo, dt_val, urgencia);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            btn_continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item, meta, tipo, dt_val;
                    int urgencia;

                    item= txt_item_pedido.getText().toString();
                    meta= txt_meta_qtd.getText().toString();
                    tipo= "";
                    //tipo= txt_tipo_pedido.getSelectedItem().toString();
                    dt_val= txt_dt_validade_pedido.getText().toString();
                    urgencia= bar_nivel_urgencia.getProgress();


                    EdicaoCadastroPedidoDoacao act= (EdicaoCadastroPedidoDoacao) getActivity();

                    //altera infos de Pedido da activity
                    act.setInfos(item, meta, tipo, dt_val, urgencia);

                    FragmentManager fm= act.getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    CadastroPedidosDeDoacaoEnderecoefimFragment cadastro_enderecoefim= new CadastroPedidosDeDoacaoEnderecoefimFragment(CadastroPedidosDeDoacaoEnderecoefimFragment.EDICAO);
                    ft.replace(R.id.place_holder_info_edicao_cadastro_pedido_doacao, cadastro_enderecoefim);
                    ft.commit();
                }
            });
        }

        return view;
    }

    public void setDadosView(String i, String m, String t, String d, int u) {
        txt_item_pedido.setText(i);
        txt_meta_qtd.setText(m);
        //txt_tipo_pedido.setText(t);
        txt_dt_validade_pedido.setText(d);
        bar_nivel_urgencia.setProgress(u);
    }
}
