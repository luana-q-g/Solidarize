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

public class PerfilUsuarioFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_perfil_usuario, container, false);

        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appBar_perfil_usuario);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_perfil_usuario);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager pager = (ViewPager) view.findViewById(R.id.view_pager_perfil_usuario);

        NavDrawMenu act = (NavDrawMenu) getActivity();

        int tipo_usuario= 1;
        //tipo=1 pessoa, tipo=2 instituicao
        //constroi o tab layout com o tab adapter de cada tipo de usuario
        if(tipo_usuario==1){
            TabAdapterPerfilPessoa tabsAdapterPessoa = new TabAdapterPerfilPessoa(act.getSupportFragmentManager());
            pager.setAdapter(tabsAdapterPessoa);
        }
        else{
            TabAdapterPerfilInstituicao tabsAdapterInstituicao = new TabAdapterPerfilInstituicao(act.getSupportFragmentManager());
            pager.setAdapter(tabsAdapterInstituicao);
        }

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
