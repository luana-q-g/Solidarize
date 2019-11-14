package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

public class FeedInstituicoesFragment extends Fragment {
    private ListView lista_instituicoes;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference instituicoes= BD.child("instituicao");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_feed_instituicoes, container, false);

        lista_instituicoes= view.findViewById(R.id.list_instituicoes);

        FirebaseListOptions<Instituicao> instituicoes_options= new FirebaseListOptions.Builder<Instituicao>()
                .setLayout(R.layout.item_listagem_instituicao)
                .setQuery(instituicoes, Instituicao.class)
                .setLifecycleOwner(this)
                .build();

        final AdapterFeedInstituicoes adapter= new AdapterFeedInstituicoes(instituicoes_options);

        lista_instituicoes.setAdapter(adapter);

        lista_instituicoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Instituicao instituicao_selecionada = adapter.getItem(position);

                //transfere o id da instituicao para o perfil
                NavDrawMenu act= (NavDrawMenu) getActivity();
                act.setIdInstituicao(instituicao_selecionada.getId());

                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                PerfilVerTodasInstituicoesFragment perfil_instituicao= new PerfilVerTodasInstituicoesFragment();
                ft.replace(R.id.place_holder_nav_draw, perfil_instituicao).addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }
}
