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
import com.projetointegrador.solidarize.BEAN.PedidoDeDoacao;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

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

        final AdapterFeedPedidosDoacao adapter= new AdapterFeedPedidosDoacao(pedidos_options);

        lista_pedidos.setAdapter(adapter);

        lista_pedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PedidoDeDoacao pedido_selecionado = adapter.getItem(position);

                //transfere o id do pedido para o perfil
                NavDrawMenu act= (NavDrawMenu) getActivity();
                act.setIdPedidoDoacao(pedido_selecionado.getId());

                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                PerfilPedidoDoacaoFragment perfil_pedido= new PerfilPedidoDoacaoFragment();
                ft.replace(R.id.place_holder_nav_draw, perfil_pedido).addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }
}
