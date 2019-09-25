package com.projetointegrador.solidarize.VIEW.NavDrawer;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabAdapterPerfilPessoa extends FragmentStatePagerAdapter {

        private String[] tabTitles = new String[]{"Perfil", "Eventos"};

        public TabAdapterPerfilPessoa(FragmentManager fm){
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
                    TabLayoutPerfilPessoaPerfilFragment tab1 = new TabLayoutPerfilPessoaPerfilFragment();
                    return tab1;

                case 1:
                    TabLayoutPerfilEventosFragment tab2 = new TabLayoutPerfilEventosFragment();
                    return tab2;

                default:
                    return null;
            }
        }
}
