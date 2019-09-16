package com.projetointegrador.solidarize.VIEW.NavDrawer;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabAdapterAcoesUsuarioEventos extends FragmentStatePagerAdapter {

        private String[] tabTitles = new String[]{"Salvos", "Confirmados"};

        public TabAdapterAcoesUsuarioEventos(FragmentManager fm){
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
                    TabLayoutAcoesUsuarioEventosSalvosFragment tab1 = new TabLayoutAcoesUsuarioEventosSalvosFragment();
                    return tab1;

                case 1:
                    TabLayoutAcoesUsuarioEventosConfirmadosFragment tab2 = new TabLayoutAcoesUsuarioEventosConfirmadosFragment();
                    return tab2;

                default:
                    return null;
            }
        }
}
