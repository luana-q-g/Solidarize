package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.CadastroEvento;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabLayoutPerfilEventosFragment extends Fragment {
    private TextView lbl_existencia_eventos;
    private ListView lista_eventos_criados;
    private Button btn_criar_evento;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference eventos= BD.child("evento");

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
        //como verificar se tem evento para esvaziar a caixa "lbl_existencia_eventos"

        FirebaseListOptions<Evento> eventos_options= new FirebaseListOptions.Builder<Evento>()
                .setLayout(R.layout.item_edicao_evento)
                .setQuery(eventos, Evento.class)
                .setLifecycleOwner(this)
                .build();

        AdapterListaPerfilEventos adapter= new AdapterListaPerfilEventos(eventos_options);

        lista_eventos_criados.setAdapter(adapter);

        btn_criar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_cadastro_evento= new Intent(getActivity().getApplicationContext(), CadastroEvento.class);
                startActivity(i_cadastro_evento);
            }
        });

        return view;
    }
}
