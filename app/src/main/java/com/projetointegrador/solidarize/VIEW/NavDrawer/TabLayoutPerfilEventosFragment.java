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
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.DAO.EventoDAO;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.CadastroEvento;
import com.projetointegrador.solidarize.VIEW.EdicaoCadastroEvento;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabLayoutPerfilEventosFragment extends Fragment {
    private TextView lbl_existencia_eventos;
    private ListView lista_eventos_criados;
    private Button btn_criar_evento;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference eventos= BD.child("evento");

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

        //fazer a autenticação de quais são os eventos criados pela pessoa!!!

        FirebaseListOptions<Evento> eventos_options= new FirebaseListOptions.Builder<Evento>()
                .setLayout(R.layout.item_edicao_evento)
                .setQuery(eventos, Evento.class)
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
        //resgatando posição do item no listView
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int list_position= info.position;

        //pegando id do evento que foi selecionado
        String id_evento= adapter.getItem(list_position).getId();

        switch (item.getItemId()){
            case R.id.item_editar:
                Intent evento_edicao= new Intent(getActivity(), EdicaoCadastroEvento.class);
                evento_edicao.putExtra("id", id_evento);
                startActivity(evento_edicao);

                return true;

            case R.id.item_excluir:
                //FAZER CAIXA DE TEXTO PARA CONFIRMAR!!

                EventoDAO eventoDAO= new EventoDAO();
                eventoDAO.excluirUsuarioEvento(id_evento);

                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
