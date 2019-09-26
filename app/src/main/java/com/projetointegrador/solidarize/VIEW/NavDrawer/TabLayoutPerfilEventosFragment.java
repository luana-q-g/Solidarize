package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

    private ArrayList<Evento> eventos= new ArrayList<>();
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

        adapter = new AdapterListaPerfilEventos(getActivity().getApplicationContext(), eventos);
        lista_eventos_criados.setAdapter(adapter);

        //dados.addValueEventListener(new EscutadorFirebase());

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
