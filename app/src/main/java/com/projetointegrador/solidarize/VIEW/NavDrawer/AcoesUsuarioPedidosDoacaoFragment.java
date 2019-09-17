package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

public class AcoesUsuarioPedidosDoacaoFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_acoes_usuario_pedidos_doacao, container, false);

        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appBar_acoes_usuario_pedidos_doacao);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_acoes_usuario_pedidos_doacao);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager pager = (ViewPager) view.findViewById(R.id.view_pager_acoes_usuario_pedidos_doacao);

        NavDrawMenu act = (NavDrawMenu) getActivity();

        //constroi o tab layout
        TabAdapterAcoesUsuarioPedidosDoacao tabsAdapter = new TabAdapterAcoesUsuarioPedidosDoacao(act.getSupportFragmentManager());

        pager.setAdapter(tabsAdapter);

        tabLayout.setupWithViewPager(pager);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}
