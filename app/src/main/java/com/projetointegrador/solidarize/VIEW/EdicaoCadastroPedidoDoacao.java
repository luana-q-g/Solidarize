package com.projetointegrador.solidarize.VIEW;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class EdicaoCadastroPedidoDoacao extends AppCompatActivity {
    private FrameLayout place_holder;

    private PedidoDeDoacao pedido_de_doacao= new PedidoDeDoacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_cadastro_pedido_doacao);

        place_holder= findViewById(R.id.place_holder_info_edicao_cadastro_pedido_doacao);

        String id_pedido_doacao= getIntent().getStringExtra("id");
        pedido_de_doacao.setId(id_pedido_doacao);

        //abertura do fragment
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        CadastroPedidosDeDoacaoInfosFragment cadastro_infos= new CadastroPedidosDeDoacaoInfosFragment(CadastroPedidosDeDoacaoInfosFragment.EDICAO);
        ft.replace(R.id.place_holder_info_edicao_cadastro_pedido_doacao, cadastro_infos);
        ft.commit();
    }

    public void setInfos(String item_pedido, String meta_qtd, String tipo_pedido, String dt_validade_pedido, String nivel_urgencia){
        this.pedido_de_doacao.setItem(item_pedido);
        this.pedido_de_doacao.setMeta_qtd(meta_qtd);
        this.pedido_de_doacao.setTipo_pedido(tipo_pedido);
        this.pedido_de_doacao.setDt_validade(dt_validade_pedido);
        this.pedido_de_doacao.setNivel_urgencia(nivel_urgencia);
    }

    public void setEnderecoEFim(String email, String es, String ci, String rua, String com){
        this.pedido_de_doacao.setEmailUsuario(email);
        this.pedido_de_doacao.setEstado(es);
        this.pedido_de_doacao.setCidade(ci);
        this.pedido_de_doacao.setRua(rua);
        this.pedido_de_doacao.setComplemento(com);
    }

    public PedidoDeDoacao getPedido_de_doacao() {
        return pedido_de_doacao;
    }

    public void setPedido_de_doacao(PedidoDeDoacao pedido_de_doacao) {
        this.pedido_de_doacao = pedido_de_doacao;
    }
}
