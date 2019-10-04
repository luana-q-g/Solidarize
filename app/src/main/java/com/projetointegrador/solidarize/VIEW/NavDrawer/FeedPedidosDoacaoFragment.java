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
import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.R;

public class FeedPedidosDoacaoFragment extends Fragment {
    private ListView lista_pedidos;

    private DatabaseReference BD= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference pedidos= BD.child("pedidoDeDoacao");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_feed_pedidos_doacao, container, false);

        lista_pedidos= view.findViewById(R.id.list_pedidos_doacao);

        FirebaseListOptions<PedidoDeDoacao> pedidos_options= new FirebaseListOptions.Builder<PedidoDeDoacao>()
                .setLayout(R.layout.item_listagem_pedidos_doacao)
                .setQuery(pedidos, PedidoDeDoacao.class)
                .setLifecycleOwner(this)
                .build();

        AdapterFeedPedidosDoacao adapter= new AdapterFeedPedidosDoacao(pedidos_options);

        lista_pedidos.setAdapter(adapter);

        return view;
    }
}
