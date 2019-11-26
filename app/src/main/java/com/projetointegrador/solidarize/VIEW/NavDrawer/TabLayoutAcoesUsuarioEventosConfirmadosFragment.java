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
import com.projetointegrador.solidarize.BEAN.ConfirmaEvento;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.ConfirmaEventoDAO;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TabLayoutAcoesUsuarioEventosConfirmadosFragment extends Fragment {
    private ListView lista_eventos_confirmados;
    private TextView lbl_existencia_eventos;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference confirmaEvento= BD.child("confirmaEvento");

    private AdapterListaEventosConfirmados adapter;

    private String id_usuario;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab_layout_acoes_usuario_eventos_confirmados, container, false);

        lista_eventos_confirmados= view.findViewById(R.id.lista_eventos_confirmados);
        lbl_existencia_eventos= view.findViewById(R.id.lbl_existencia_eventos);

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
        DatabaseReference referencia_usuario_evento= confirmaEvento.child(id_usuario);

        FirebaseListOptions<ConfirmaEvento> eventos_confirmados_options= new FirebaseListOptions.Builder<ConfirmaEvento>()
                .setLayout(R.layout.item_edicao_evento)
                .setQuery(referencia_usuario_evento, ConfirmaEvento.class)
                .setLifecycleOwner(this)
                .build();

        adapter= new AdapterListaEventosConfirmados(eventos_confirmados_options, lbl_existencia_eventos);

        lista_eventos_confirmados.setAdapter(adapter);

        //context menu
        registerForContextMenu(lista_eventos_confirmados);

        lista_eventos_confirmados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConfirmaEvento evento_selecionado = adapter.getItem(position);

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

        return view;
    }

    public class AdapterListaEventosConfirmados extends FirebaseListAdapter<ConfirmaEvento> {
        private TextView lbl_existencia_eventos;

        public AdapterListaEventosConfirmados(FirebaseListOptions options, TextView lbl_existencia_eventos){
            super (options);
            this.lbl_existencia_eventos= lbl_existencia_eventos;
        }

        protected void populateView (View v, ConfirmaEvento e, int position){
            TextView lbl_nome;
            TextView lbl_id;

            lbl_nome= v.findViewById(R.id.lbl_nome_evento);
            lbl_id= v.findViewById(R.id.lbl_id_evento);

            lbl_nome.setText(e.getNomeEvento());
            lbl_id.setText(e.getIdEvento());
        }

        @Override
        public void onDataChanged(){
            //se chamar esse método, esvazia
            if(getCount()==0){
                lbl_existencia_eventos.setText("você não confirmou nenhum evento...");
            }
            else{
                lbl_existencia_eventos.setText("");
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_context_editar_confirmados, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (getUserVisibleHint()) {
            //resgatando posição do item no listView
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int list_position = info.position;

            //pegando id do evento que foi selecionado
            final ConfirmaEvento confirmaEvento = (ConfirmaEvento) lista_eventos_confirmados.getItemAtPosition(list_position);
            final String id_evento = confirmaEvento.getIdEvento();

            //final String id_evento= adapter.getItem(list_position).getIdEvento();

            if(item.getItemId() == R.id.item_editar_confirmados){
                AlertDialog.Builder alert_evento = new AlertDialog.Builder(getActivity());
                alert_evento.setTitle("Deseja retirar o evento da lista dos confirmados?");
                alert_evento.setMessage("A confirmação do evento tem importância para o organizador do evento!");
                alert_evento.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConfirmaEventoDAO confirmaEventoDAO = new ConfirmaEventoDAO();
                        confirmaEventoDAO.excluirEventoConfirmado(id_usuario, id_evento);
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
