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
import com.projetointegrador.solidarize.BEAN.Evento;
import com.projetointegrador.solidarize.R;

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

        AdapterFeedEventos adapter= new AdapterFeedEventos(eventos_options);

        lista_eventos.setAdapter(adapter);

        return view;
    }
}
