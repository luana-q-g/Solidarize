package com.projetointegrador.solidarize.VIEW.NavDrawer;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabAdapterPerfilInstituicao extends FragmentStatePagerAdapter {
    private String[] tabTitles = new String[]{"Perfil", "Eventos", "Pedidos de Doação"};

    public TabAdapterPerfilInstituicao(FragmentManager fm){
        super(fm);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    // overriding getPageTitle()
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                TabLayoutPerfilInstituicaoPerfilFragment tab1 = new TabLayoutPerfilInstituicaoPerfilFragment();
                return tab1;

            case 1:
                TabLayoutPerfilEventosFragment tab2 = new TabLayoutPerfilEventosFragment();
                return tab2;

            case 2:
                TabLayoutPerfilPedidosDoacaoFragment tab3 = new TabLayoutPerfilPedidosDoacaoFragment();
                return tab3;

            default:
                return null;
        }
    }
}
