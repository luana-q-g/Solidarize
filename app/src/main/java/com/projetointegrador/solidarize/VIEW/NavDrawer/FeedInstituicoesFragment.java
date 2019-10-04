package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projetointegrador.solidarize.BEAN.Instituicao;
import com.projetointegrador.solidarize.R;

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

        AdapterFeedInstituicoes adapter= new AdapterFeedInstituicoes(instituicoes_options);

        lista_instituicoes.setAdapter(adapter);

        return view;
    }
}
