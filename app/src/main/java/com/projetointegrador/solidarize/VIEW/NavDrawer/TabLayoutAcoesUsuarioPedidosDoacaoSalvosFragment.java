package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.SalvaPedidoDeDoacao;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.SalvaPedidoDeDoacaoDAO;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TabLayoutAcoesUsuarioPedidosDoacaoSalvosFragment extends Fragment {
    private ListView lista_pedidos_salvos;
    private TextView lbl_existencia_pedidos;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference salvaPedidoDeDoacao= BD.child("salvaPedidoDeDoacao");

    private AdapterListaPedidosSalvos adapter;

    private String id_usuario;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab_layout_acoes_usuario_pedidos_doacao_salvos, container, false);

        lista_pedidos_salvos= view.findViewById(R.id.lista_pedidos_de_doacao_salvos);
        lbl_existencia_pedidos= view.findViewById(R.id.lbl_existencia_pedidos);

        id_usuario= "";
        if (UsuarioLogado.getInstance().getUsuario().getTipo_usuario().contentEquals("pessoa")) {
            Pessoa usuario_pessoa = (Pessoa) UsuarioLogado.getInstance().getUsuario();
            id_usuario= usuario_pessoa.getId();
        }
        else{
            Instituicao usuario_instituicao = (Instituicao) UsuarioLogado.getInstance().getUsuario();
            id_usuario= usuario_instituicao.getId();
        }

        //pegar nó especifico do usuario p listar eventos salvos
        DatabaseReference referencia_usuario_pedido= salvaPedidoDeDoacao.child(id_usuario);

        FirebaseListOptions<SalvaPedidoDeDoacao> pedidos_salvos_options= new FirebaseListOptions.Builder<SalvaPedidoDeDoacao>()
                .setLayout(R.layout.item_edicao_pedidos_doacao)
                .setQuery(referencia_usuario_pedido, SalvaPedidoDeDoacao.class)
                .setLifecycleOwner(this)
                .build();

        adapter= new AdapterListaPedidosSalvos(pedidos_salvos_options, lbl_existencia_pedidos);

        lista_pedidos_salvos.setAdapter(adapter);

        //context menu
        registerForContextMenu(lista_pedidos_salvos);

        lista_pedidos_salvos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SalvaPedidoDeDoacao pedido_selecionado = adapter.getItem(position);

                //transfere o id do pedido para o perfil
                NavDrawMenu act= (NavDrawMenu) getActivity();
                act.setIdPedidoDoacao(pedido_selecionado.getIdPedido());

                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                PerfilPedidoDoacaoFragment perfil_pedido= new PerfilPedidoDoacaoFragment();
                ft.replace(R.id.place_holder_nav_draw, perfil_pedido).addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }

    public class AdapterListaPedidosSalvos extends FirebaseListAdapter<SalvaPedidoDeDoacao> {
        private TextView lbl_existencia_pedidos;

        public AdapterListaPedidosSalvos(FirebaseListOptions options, TextView lbl_existencia_pedidos){
            super (options);
            this.lbl_existencia_pedidos= lbl_existencia_pedidos;
        }

        protected void populateView (View v, SalvaPedidoDeDoacao p, int position){
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
                lbl_existencia_pedidos.setText("nenhum pedido de doação foi salvo...");
            }
            else{
                lbl_existencia_pedidos.setText("");
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_context_editar_salvar, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (getUserVisibleHint()) {
            //resgatando posição do item no listView
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int list_position = info.position;

            //pegando id do evento que foi selecionado
            SalvaPedidoDeDoacao salvaPedidoDeDoacao = (SalvaPedidoDeDoacao) lista_pedidos_salvos.getItemAtPosition(list_position);
            final String id_pedido = salvaPedidoDeDoacao.getIdPedido();

            if(item.getItemId() == R.id.item_editar_salvo){
                AlertDialog.Builder alert_evento = new AlertDialog.Builder(getActivity());
                alert_evento.setTitle("Deseja retirar o pedido de doação da lista dos salvos?");
                alert_evento.setMessage("Salvar o pedido de doação pode te ajudar a lembrar dele");
                alert_evento.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SalvaPedidoDeDoacaoDAO salvaPedidoDeDoacaoDAO = new SalvaPedidoDeDoacaoDAO();
                        salvaPedidoDeDoacaoDAO.excluirPedidoSalvo(id_usuario, id_pedido);
                    }
                });

                alert_evento.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alert_evento.create();
                alert_evento.show();

                return true;
            }
            else{
                return super.onContextItemSelected(item);
            }

        }
        return false;
    }
}
