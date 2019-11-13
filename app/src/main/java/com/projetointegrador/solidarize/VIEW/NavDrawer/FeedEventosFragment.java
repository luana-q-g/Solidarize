package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.content.Intent;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

public class FeedEventosFragment extends Fragment {
    private ListView lista_eventos;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference eventos= BD.child("evento");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_feed_eventos, container, false);

        lista_eventos= view.findViewById(R.id.list_eventos);

        FirebaseListOptions<Evento> eventos_options= new FirebaseListOptions.Builder<Evento>()
                .setLayout(R.layout.item_listagem_evento)
                .setQuery(eventos, Evento.class)
                .setLifecycleOwner(this)
                .build();

        final AdapterFeedEventos adapter= new AdapterFeedEventos(eventos_options);

        lista_eventos.setAdapter(adapter);

        lista_eventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento evento_selecionado = adapter.getItem(position);

                //transfere o id do evento para o perfil
                NavDrawMenu act= (NavDrawMenu) getActivity();
                act.setIdEvento(evento_selecionado.getId());

                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                PerfilEventoFragment perfil_evento= new PerfilEventoFragment();
                ft.replace(R.id.place_holder_nav_draw, perfil_evento).addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }
}
