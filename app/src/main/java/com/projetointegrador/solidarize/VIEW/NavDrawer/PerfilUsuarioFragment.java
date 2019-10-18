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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.projetointegrador.solidarize.BEAN.Pessoa;
import com.projetointegrador.solidarize.BEAN.UsuarioLogado;
import com.projetointegrador.solidarize.R;
import com.projetointegrador.solidarize.VIEW.NavDrawMenu;

public class PerfilUsuarioFragment extends Fragment {

    private FirebaseAuth auth_usuario= FirebaseAuth.getInstance();

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

        if(auth_usuario.getCurrentUser() != null){
            NavDrawMenu act = (NavDrawMenu) getActivity();

            //recupera usuario logado
            UsuarioLogado.getInstance().getUsuario();

            if (UsuarioLogado.getInstance().getUsuario().getTipo_usuario().contentEquals("pessoa")) {
                TabAdapterPerfilPessoa tabsAdapterPessoa = new TabAdapterPerfilPessoa(act.getSupportFragmentManager());
                pager.setAdapter(tabsAdapterPessoa);

                // os dados são recuperados com:
                // Pessoa usuario_pessoa = (Pessoa) UsuarioLogado.getInstance().getUsuario();
            }
            else{
                TabAdapterPerfilInstituicao tabsAdapterInstituicao = new TabAdapterPerfilInstituicao(act.getSupportFragmentManager());
                pager.setAdapter(tabsAdapterInstituicao);

                // os dados são recuperados com:
                // Instituicao i1 = (Instituicao) UsuarioLogado.getInstance().getUsuario();
            }
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
