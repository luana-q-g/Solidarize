package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioPedidoDoacao;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.CadastroUsuarioPedidoDoacaoDAO;
import com.projetointegrador.solidarize.DAO.PedidosDeDoacaoDAO;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.CadastroPedidosDeDoacao;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroPedidoDoacao;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabLayoutPerfilPedidosDoacaoFragment extends Fragment {
    private TextView lbl_existencia_pedidos_doacao;
    private ListView lista_pedidos_criados;
    private Button btn_criar_pedido;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference cadastroUsuarioPedido= BD.child("cadastroUsuarioPedido");

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

        //inserindo relação tipo "foreign key" para identificar que usuario que cria tal evento
        String id_usuario= "";
        if (UsuarioLogado.getInstance().getUsuario().getTipo_usuario().contentEquals("pessoa")) {
            Pessoa usuario_pessoa = (Pessoa) UsuarioLogado.getInstance().getUsuario();
            id_usuario= usuario_pessoa.getId();
        }
        else{
            Instituicao usuario_instituicao = (Instituicao) UsuarioLogado.getInstance().getUsuario();
            id_usuario= usuario_instituicao.getId();
        }

        //pegar nó especifico do usuario p listar eventos
        DatabaseReference referencia_usuario_pedido= cadastroUsuarioPedido.child(id_usuario);

        FirebaseListOptions<CadastroUsuarioPedidoDoacao> pedidos_options= new FirebaseListOptions.Builder<CadastroUsuarioPedidoDoacao>()
                .setLayout(R.layout.item_edicao_pedidos_doacao)
                .setQuery(referencia_usuario_pedido, CadastroUsuarioPedidoDoacao.class)
                .setLifecycleOwner(this)
                .build();

        adapter = new AdapterListaPerfilPedidosDoacao(pedidos_options);
        lista_pedidos_criados.setAdapter(adapter);

        //verifica se existem pedidos
        if(adapter.getCount() == 0){
            lbl_existencia_pedidos_doacao.setText("");
        }

        //context menu
        registerForContextMenu(lista_pedidos_criados);

        btn_criar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_cadastro_pedido= new Intent(getActivity().getApplicationContext(), CadastroPedidosDeDoacao.class);
                startActivity(i_cadastro_pedido);
            }
        });

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_context_editar_excluir, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //resgatando posição do item no listView
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int list_position= info.position;

        //pegando id do pedido que foi selecionado
        String id_pedido= adapter.getItem(list_position).getIdPedido();

        switch (item.getItemId()){
            case R.id.item_editar:
                Intent pedido_edicao= new Intent(getActivity(), EdicaoCadastroPedidoDoacao.class);
                pedido_edicao.putExtra("id", id_pedido);
                startActivity(pedido_edicao);

                return true;

            case R.id.item_excluir:
                //FAZER CAIXA DE TEXTO PARA CONFIRMAR!!

                PedidosDeDoacaoDAO pedidosDeDoacaoDAO= new PedidosDeDoacaoDAO();
                pedidosDeDoacaoDAO.excluirPedidoDoacao(id_pedido);

                //id_usuario para excluir evento específico do no de usuario
                String id_usuario= "";
                if (UsuarioLogado.getInstance().getUsuario().getTipo_usuario().contentEquals("pessoa")) {
                    Pessoa usuario_pessoa = (Pessoa) UsuarioLogado.getInstance().getUsuario();
                    id_usuario= usuario_pessoa.getId();
                }
                else{
                    Instituicao usuario_instituicao = (Instituicao) UsuarioLogado.getInstance().getUsuario();
                    id_usuario= usuario_instituicao.getId();
                }

                CadastroUsuarioPedidoDoacaoDAO cadastroUsuarioPedidoDoacaoDAO= new CadastroUsuarioPedidoDoacaoDAO();
                cadastroUsuarioPedidoDoacaoDAO.excluirCadastroUsuarioPedido(id_usuario, id_pedido);

                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
