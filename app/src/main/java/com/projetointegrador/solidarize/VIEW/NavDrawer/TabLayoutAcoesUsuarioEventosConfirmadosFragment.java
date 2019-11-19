package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.projetointegrador.solidarize.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabLayoutAcoesUsuarioEventosConfirmadosFragment extends Fragment {
    private ListView lista_eventos_confirmados;
    private TextView lbl_existencia_eventos;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference confirmaEvento= BD.child("confirmaEvento");

    private AdapterListaEventosConfirmados adapter;

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

        String id_usuario= "";
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
}
