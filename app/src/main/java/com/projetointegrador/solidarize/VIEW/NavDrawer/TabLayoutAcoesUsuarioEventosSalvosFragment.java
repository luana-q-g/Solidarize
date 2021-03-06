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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.CadastroUsuarioEvento;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.SalvaEvento;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.DAO.SalvaEventoDAO;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TabLayoutAcoesUsuarioEventosSalvosFragment extends Fragment {
    private ListView lista_eventos_salvos;
    private TextView lbl_existencia_eventos;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference salvaEvento= BD.child("salvaEvento");

    private AdapterListaEventosSalvos adapter;

    private String id_usuario;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab_layout_acoes_usuario_eventos_salvos, container, false);

        lista_eventos_salvos= view.findViewById(R.id.lista_eventos_salvos);
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
        DatabaseReference referencia_usuario_evento= salvaEvento.child(id_usuario);

        FirebaseListOptions<SalvaEvento> eventos_salvos_options= new FirebaseListOptions.Builder<SalvaEvento>()
                .setLayout(R.layout.item_edicao_evento)
                .setQuery(referencia_usuario_evento, SalvaEvento.class)
                .setLifecycleOwner(this)
                .build();

        adapter= new AdapterListaEventosSalvos(eventos_salvos_options, lbl_existencia_eventos);

        lista_eventos_salvos.setAdapter(adapter);

        //context menu
        registerForContextMenu(lista_eventos_salvos);

        lista_eventos_salvos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SalvaEvento evento_selecionado = adapter.getItem(position);

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

    public class AdapterListaEventosSalvos extends FirebaseListAdapter<SalvaEvento> {
        private TextView lbl_existencia_eventos;

        public AdapterListaEventosSalvos(FirebaseListOptions options, TextView lbl_existencia_eventos){
            super (options);
            this.lbl_existencia_eventos= lbl_existencia_eventos;
        }

        protected void populateView (View v, SalvaEvento e, int position){
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
                lbl_existencia_eventos.setText("nenhum evento foi salvo...");
            }
            else{
                lbl_existencia_eventos.setText("");
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
            SalvaEvento salvaEvento = (SalvaEvento) lista_eventos_salvos.getItemAtPosition(list_position);
            final String id_evento = salvaEvento.getIdEvento();

            //final String id_evento= adapter.getItem(list_position).getIdEvento();

            if(item.getItemId() == R.id.item_editar_salvo){
                AlertDialog.Builder alert_evento = new AlertDialog.Builder(getActivity());
                alert_evento.setTitle("Deseja retirar o evento da lista dos salvos?");
                alert_evento.setMessage("Salvar o evento pode te ajudar a lembrar dele");
                alert_evento.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SalvaEventoDAO salvaEventoDAO = new SalvaEventoDAO();
                        salvaEventoDAO.excluirEventoSalvo(id_usuario, id_evento);
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