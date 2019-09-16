package com.projetointegrador.solidarize.VIEW.NavDrawer;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabAdapterAcoesUsuarioPedidosDoacao extends FragmentStatePagerAdapter {

        private String[] tabTitles = new String[]{"Salvos", "Confirmados"};

        public TabAdapterAcoesUsuarioPedidosDoacao(FragmentManager fm){
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
                    TabLayoutAcoesUsuarioPedidosDoacaoSalvosFragment tab1 = new TabLayoutAcoesUsuarioPedidosDoacaoSalvosFragment();
                    return tab1;

                case 1:
                    TabLayoutAcoesUsuarioPedidosDoacaoConfirmadosFragment tab2 = new TabLayoutAcoesUsuarioPedidosDoacaoConfirmadosFragment();
                    return tab2;

                default:
                    return null;
            }
        }
}
