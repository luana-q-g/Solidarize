package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioEvento;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.CadastroUsuarioEventoDAO;
import com.projetointegrador.solidarize.DAO.EventoDAO;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.CadastroEvento;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroEvento;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TabLayoutPerfilEventosFragment extends Fragment {
    private TextView lbl_existencia_eventos;
    private ListView lista_eventos_criados;
    private ImageButton btn_criar_evento;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference cadastroUsuarioEvento= BD.child("cadastroUsuarioEvento");

    private AdapterListaPerfilEventos adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab_layout_perfil_eventos, container, false);

        lbl_existencia_eventos= view.findViewById(R.id.lbl_existencia_eventos);
        lista_eventos_criados= view.findViewById(R.id.lista_eventos_criados);
        btn_criar_evento= view.findViewById(R.id.btn_criar_evento);

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
        DatabaseReference referencia_usuario_evento= cadastroUsuarioEvento.child(id_usuario);

        FirebaseListOptions<CadastroUsuarioEvento> eventos_options= new FirebaseListOptions.Builder<CadastroUsuarioEvento>()
                .setLayout(R.layout.item_edicao_evento)
                .setQuery(referencia_usuario_evento, CadastroUsuarioEvento.class)
                .setLifecycleOwner(this)
                .build();

        adapter= new AdapterListaPerfilEventos(eventos_options);

        lista_eventos_criados.setAdapter(adapter);

        //verifica se existem eventos
        if(adapter.getCount() == 0){
            lbl_existencia_eventos.setText("");
        }

        //context menu
        registerForContextMenu(lista_eventos_criados);

        lista_eventos_criados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CadastroUsuarioEvento evento_selecionado = adapter.getItem(position);

                //transfere o id do evento para o perfil
                NavDrawMenu act= (NavDrawMenu) getActivity();
                act.setIdEvento(evento_selecionado.getIdEvento());

                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();
                PerfilEventoFragment perfil_evento= new PerfilEventoFragment();

                ft.replace(R.id.place_holder_nav_draw, perfil_evento).addToBackStack(null);
                ft.commit();
            }
        });

        btn_criar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_cadastro_evento= new Intent(getActivity().getApplicationContext(), CadastroEvento.class);
                startActivity(i_cadastro_evento);
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
        if (getUserVisibleHint()) {
            //resgatando posição do item no listView
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int list_position = info.position;

            //pegando id do evento que foi selecionado
            CadastroUsuarioEvento evento = (CadastroUsuarioEvento) lista_eventos_criados.getItemAtPosition(list_position);
            final String id_evento = evento.getIdEvento();

            //final String id_evento= adapter.getItem(list_position).getIdEvento();

            switch (item.getItemId()) {
                case R.id.item_editar:
                    Intent evento_edicao = new Intent(getActivity(), EdicaoCadastroEvento.class);
                    evento_edicao.putExtra("id", id_evento);
                    startActivity(evento_edicao);

                    return true;

                case R.id.item_excluir:
                    //CAIXA DE TEXTO PARA CONFIRMAR!!
                    AlertDialog.Builder alert_evento = new AlertDialog.Builder(getActivity());
                    alert_evento.setTitle("Deseja mesmo excluir o Evento?");
                    alert_evento.setMessage("Essa ação não pode ser desfeita!!");
                    alert_evento.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EventoDAO eventoDAO = new EventoDAO();
                            eventoDAO.excluirUsuarioEvento(id_evento);

                            //id_usuario para excluir evento específico do no de usuario
                            String id_usuario = "";
                            if (UsuarioLogado.getInstance().getUsuario().getTipo_usuario().contentEquals("pessoa")) {
                                Pessoa usuario_pessoa = (Pessoa) UsuarioLogado.getInstance().getUsuario();
                                id_usuario = usuario_pessoa.getId();
                            } else {
                                Instituicao usuario_instituicao = (Instituicao) UsuarioLogado.getInstance().getUsuario();
                                id_usuario = usuario_instituicao.getId();
                            }

                            CadastroUsuarioEventoDAO cadastroUsuarioEventoDAO = new CadastroUsuarioEventoDAO();
                            cadastroUsuarioEventoDAO.excluirCadastroUsuarioEvento(id_usuario, id_evento);
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

                default:
                    return super.onContextItemSelected(item);
            }
        }
        return false;
    }
}
